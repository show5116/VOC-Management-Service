package com.vms.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class AuthUtil {
    private final JwtAuthProvider provider;
    private final CustomUserDetailsService userDetailsService;
    public String getJwtFromRequest(String authToken) {
        if(StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            return authToken.substring(7);
        }
        return null;
    }
    protected String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    protected UsernamePasswordAuthenticationToken setAuthentication(HttpServletRequest request, HttpServletResponse response, String jwt, String type) throws Exception {
        Jws<Claims> jws = provider.validateToken(jwt);

        AuthToken authToken = provider.getUser(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsernameAndPlant(authToken);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if("login".equals(type)){
            if(jws.getBody().getExpiration().getTime() - new Date().getTime()  < 300000 ) {
                //System.out.println((jws.getBody().getExpiration().getTime() - new Date().getTime()) / 1000 +" 초 남음");
                if(provider.validRefreshToken(request ,String.valueOf(jws.getBody().get("id")), String.valueOf(jws.getBody().get("plant")) )){
                    response.setHeader("Authorization", "Bearer "+ provider.createToken(authentication));
                }
            }
        }

        return authentication;
    }
}
