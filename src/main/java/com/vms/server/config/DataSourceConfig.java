package com.vms.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final GlobalPropertySource propertySource;

    @Bean
    @Primary
    public DataSource customDataSource(){
        return DataSourceBuilder
                .create()
                .url(propertySource.getUrl())
                .driverClassName(propertySource.getDriverClassName())
                .username(propertySource.getUsername())
                .password(propertySource.getPassword())
                .build();
    }
}