package com.vms.server.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAuthProvider provider;
    private final AuthUtil authUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = authUtil.getJwtFromRequest(request);
        try{
            if( !"/login/common-plant".equals(request.getServletPath()) &&
                !"/login/valid-token".equals(request.getServletPath()) &&
                !"/login".equals(request.getServletPath()) ) {

                if(StringUtils.hasText(jwt) && provider.validateToken(jwt) != null ) {
                    SecurityContextHolder.getContext().setAuthentication(authUtil.setAuthentication(request,response,jwt,"login"));
                }
            }
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", AuthCode.WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", AuthCode.EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", AuthCode.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", AuthCode.WRONG_TOKEN.getCode());
        } catch (Exception e) {
            request.setAttribute("exception", AuthCode.UNKNOWN_ERROR.getCode());
        }

        filterChain.doFilter(request,response);
    }


}