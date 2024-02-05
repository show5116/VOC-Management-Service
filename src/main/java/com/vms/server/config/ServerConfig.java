package com.vms.server.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    @Value("${server.port}")
    private int serverPort;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {

     System.out.println(contextPath);
        return factory -> {
            factory.setPort(serverPort);
            factory.setContextPath(contextPath);
            // Tomcat의 추가 설정이 필요한 경우 여기에 추가
        };
    }

}
