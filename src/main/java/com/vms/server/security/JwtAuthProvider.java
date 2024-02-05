package com.vms.server.security;

import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.vms.server.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
@PropertySource("classpath:/application.properties")
public class JwtAuthProvider {

    @Value("${jwt.secretKey}")
    private String atSecretKey;

    @Value("${jwt.expireMs}")
    private int expireMs;


    protected Key getSignKey(){
    //secretKey 객체 생성 후 리턴
        return Keys.hmacShaKeyFor(atSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String createToken(Authentication authentication){

        CustomUserDetails accountContext = (CustomUserDetails)authentication.getPrincipal();
        Date now = new Date();
        Date expire = new Date(now.getTime() + expireMs);

        return Jwts.builder()
                .setSubject("authToken")
                .claim("id",accountContext.getId())
                .claim("plant",accountContext.getPlant())
                .setIssuedAt(new Date())
                .setExpiration(expire)
                .signWith(getSignKey())
                .compact();
    }

    public AuthToken getUser(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return AuthToken.builder()
                .id((String) claims.get("id"))
                .plant((String) claims.get("plant"))
                .build();
    }

    public Jws<Claims> validateToken(String authToken) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(authToken);
    }

    public boolean validRefreshToken(HttpServletRequest request,String userId, String plant) throws Exception {

        String key = getKeyTo32(plant ,userId );

        String refToken = "";
        for(Cookie cookie : request.getCookies()){
            if("refresh_token".equals(cookie.getName())){
                refToken = cookie.getValue();
            }
        }

        if( refToken.trim().length() < 1 ){
            throw new Exception("토큰이 없습니다.");
        }

        RefreshToken rfToken = new RefreshToken (SecurityUtil.decryptAES256( refToken , key) );

        if ( !SecurityUtil.getClientIp().equals(rfToken.getIp())
                || !userId.equals(rfToken.getUserId())
                || !plant.equals(rfToken.getPlant()) ) {
            throw new MalformedJwtException("발급된 IP가 다릅니다.");
        }

        return true;
    }

    /**
     * userid, plant, 접속ip 정보  AES256 암호화 하여 redis에 저장
     */
    public void createRefreshToken(HttpServletRequest request, HttpServletResponse response, AdmUserInfo member ) throws Exception {

        //redis - 데이터 만료 ms [8시간]
        //long refreshExpired = 28800000L;

        String refreshTokenKey = getKeyTo32(member.getPlant(), member.getUserId());
        //AES256으로 암호화된 token 발행
        String refreshToken =
                SecurityUtil.encryptAES256(new RefreshToken(member.getUserId(), member.getPlant(), SecurityUtil.getClientIp()).getRefreshTokenToString(), refreshTokenKey );

        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);

//        //refresh token 이 있다면 삭제
//        if(redisService.getData(refreshTokenKey) != null){
//            redisService.deleteData(refreshTokenKey);
//        }
//
//        redisService.setData(refreshTokenKey, refreshToken, refreshExpired);

        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        //8시간
        refreshTokenCookie.setMaxAge(60 * 60 * 8);
        //refreshTokenCookie.setMaxAge(60 * 1 * 1);
        response.addCookie(refreshTokenCookie);
    }

    /**
     * AES256 암호화 키 (32byte) 로 마추기
     */
    private String getKeyTo32(String userPlant, String userId) {
        String key = "RFT_"+userPlant+"_"+userId;

        if(key.length() < 32){
            StringBuilder keyBuilder = new StringBuilder(key);
            while (keyBuilder.length() < 32){
                keyBuilder.append("0");
            }
            key = keyBuilder.toString();
        } else if(key.length() > 32){
            key = key.substring(0,32);
        }

        return key;
    }

}
