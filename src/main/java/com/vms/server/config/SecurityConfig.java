package com.vms.server.config;

import com.vms.server.security.AuthLogoutFilter;
import com.vms.server.security.CustomAuthenticationSuccessHandler;
import com.vms.server.security.CustomUserDetailsService;
import com.vms.server.security.JwtAuthFilter;
import com.vms.server.security.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final MessageSource messageSource;
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthLogoutFilter authLogoutFilter;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/login", "/logout","/syscode/system-code-list", "/login/common-plant", "/login/valid-token", "/auth/**", "/api/public/**").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(authLogoutFilter, LogoutFilter.class);
//
////        http.formLogin()
////                .successHandler(authenticationSuccessHandler());
//
//        http.logout()
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .permitAll();

        http
                .authorizeRequests()
                .anyRequest().permitAll() // 모든 요청에 대해 보안 비활성화
                .and()
                .csrf().disable() // CSRF 보호 비활성화
                .headers().frameOptions().disable();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-512");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(userDetailsService, messageSource);
    }
}