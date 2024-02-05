package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminSystemCodeDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminSystemCodeDaoImpl implements AdminSystemCodeDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView) {

        StringBuilder sb = new StringBuilder();
        if (codeView) {
            sb.append("SELECT GET_SYSCODE_DESC(:plant ,:tableName ,CODE_NAME) description, CODE_NAME ");
        } else {
            sb.append("SELECT DESCRIPTION description, CODE_NAME ");
        }
        sb.append("  FROM SYS_SYSTEM_CODE_DATA ");
        sb.append(" WHERE PLANT = :plant  ");
        sb.append("   AND TABLE_NAME = :tableName ");
        sb.append(" ORDER BY description");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("tableName", tableName);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysSystemCodeDataDto.class)
        );
    }

    @Override
    public List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView, String orderBy) {
        StringBuilder sb = new StringBuilder();
        if (codeView) {
            sb.append("SELECT GET_SYSCODE_DESC(:plant ,:tableName ,CODE_NAME) description, CODE_NAME ");
        } else {
            sb.append("SELECT DESCRIPTION description, CODE_NAME ");
        }
        sb.append("  FROM SYS_SYSTEM_CODE_DATA ");
        sb.append(" WHERE PLANT = :plant  ");
        sb.append("   AND TABLE_NAME = :tableName ");
        sb.append(" ORDER BY ");
        sb.append(orderBy);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("tableName", tableName);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysSystemCodeDataDto.class)
        );
    }
}
