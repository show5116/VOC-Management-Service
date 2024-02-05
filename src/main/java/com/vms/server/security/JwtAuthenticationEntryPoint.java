package com.vms.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Autowired
    private MessageSource messageSource;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        String exception = (String)request.getAttribute("exception");
        String errorMessage ="";
        //로그인 비밀번호 틀림
        if(exception == null  ) {
            errorMessage = "no match information";
            setResponse(response, AuthCode.WRONG_PASSWORD, errorMessage);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(AuthCode.WRONG_TYPE_TOKEN.getCode())) {
            errorMessage = "Wrong type token";
            setResponse(response, AuthCode.WRONG_TYPE_TOKEN, errorMessage);
        }
        //토큰 만료된 경우
        else if(exception.equals(AuthCode.EXPIRED_TOKEN.getCode())) {
            errorMessage = "Expired Token.";
            setResponse(response, AuthCode.EXPIRED_TOKEN, errorMessage);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(AuthCode.UNSUPPORTED_TOKEN.getCode())) {
            errorMessage = "Unsupported Token";
            setResponse(response, AuthCode.UNSUPPORTED_TOKEN, errorMessage);
        }
        else {
            errorMessage = "Access Denied";
            setResponse(response, AuthCode.ACCESS_DENIED, errorMessage);
        }
    }

    private void setResponse(HttpServletResponse response, AuthCode authCode, String errorMessage ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        if(authCode.getCode().equals(AuthCode.WRONG_PASSWORD.getCode())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        Map<String, Object> responsMap = new HashMap<>();
        responsMap.put("errorCode",authCode.getCode());
        responsMap.put("localeErrorMessage",errorMessage);

        response.getWriter().write(new ObjectMapper().writeValueAsString(responsMap));
    }

}
