package com.vms.server.security;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final CustomUserDetailsService userDetailsService;
    private final MessageSource messageSource;

    public CustomAuthenticationSuccessHandler(CustomUserDetailsService userDetailsService, MessageSource messageSource) {
        this.userDetailsService = userDetailsService;
        this.messageSource = messageSource;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = authentication.getName();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        Locale preferredLocale = Locale.forLanguageTag(userDetails.getLanguageType());
        LocaleContextHolder.setLocale(preferredLocale);

    }

}
