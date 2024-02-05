package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminAdmUserInfoDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.AdmUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminAdmAdmUserInfoDaoImpl implements AdminAdmUserInfoDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<AdmUserInfoDto> getUserInfoSysFileLog(AdmUserInfoDto admUserInfoDto) {
        String plant = admUserInfoDto.getPlant();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT USER_NAME AS DESCRIPTION ");
        sb.append("        , USER_ID AS CODE ");
        sb.append("  FROM ADM_USER_INFO ");
        sb.append(" WHERE PLANT = :plant ");
        sb.append("   AND EXISTS (SELECT 'X' ");
        sb.append("                 FROM SYS_SYSTEM_CODE_DATA ");
        sb.append("                WHERE PLANT = :plant ");
        sb.append("                  AND TABLE_NAME = 'EVENT_LOG_ROLE' ");
        sb.append("                  AND ROLE_ID = CODE_NAME) ");
        sb.append(" ORDER BY USER_NAME ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmUserInfoDto.class)
        );
    }
}
