package com.vms.server.common;

import com.vms.server.util.StringCaseConverter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class CustomBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {

    public CustomBeanPropertyRowMapper(Class<T> mappedClass) {
        super(mappedClass);
        this.setPrimitivesDefaultedForNullValue(true);
    }

    @Override
    protected String underscoreName(String name) {
        return StringCaseConverter.toSnakeCase(name);
    }
}