package com.vms.server.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthLogoutFilter extends OncePerRequestFilter  {

    //private final SysClientService sysClientService;
    private final AuthUtil authUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/logout".equals(request.getServletPath()) ) {
            String jwt = authUtil.getJwtFromRequest(request);
            try {
                SecurityContextHolder.getContext().setAuthentication(authUtil.setAuthentication(request, response,jwt, "logout"));
                //sysClientService.saveLog(new LoginLogDto(LoginLogDto.LoginType.LOGOUT));
                response.getWriter().write("logout");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request,response);
    }
}
