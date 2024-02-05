package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.QmsLsMasterDto;
import com.vms.server.qms.repository.dao.LSManagerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class LSManagerDaoImpl implements LSManagerDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<QmsLsMasterDto> getViewSelect(String plant, String systemName, String qmsNumber) {

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT A.ISSUE_TITLE\n");
        sb.append("       ,A.DIVISION\n");
        sb.append("       ,A.DEPARTMENT\n");
        sb.append("       ,A.ISSUE_CATEGORY\n");
        sb.append("       ,A.PROCESS\n");
        sb.append("       ,A.SUPPLIER\n");
        sb.append("       ,A.CAUSE\n");
        sb.append("       ,A.ISSUE_SUMMARY\n");
        sb.append("       ,A.CAUSE_COMMENT\n");
        sb.append("       ,A.MEASURES\n");
        sb.append("       ,TO_CHAR(TO_DATE(A.REG_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') AS REG_DATE\n");
        sb.append("       , GET_USERID_DESC_ONLY(A.PLANT, A.REG_USER) AS REG_USER_NAME\n");
        sb.append("       ,A.REG_USER\n");
        sb.append("       , A.CLOSED_FLAG\n");
        sb.append("       ,GET_USER_DEPT(A.REG_USER) AS USER_DEPT\n");
        sb.append("       , GET_DEPT_NAME_ONLY(A.DEPARTMENT) AS DEPT_NAME\n"); //2022.10.05 KYT LS, EVN 중간 점검 개선 사항 수정
        sb.append("   FROM QMS_LS_MASTER A\n");
        sb.append("  WHERE A.PLANT = :plant \n");
        sb.append("    AND A.SYSTEM_NAME = :systemName \n");
        sb.append("    AND A.QMS_NUMBER = :qmsNumber \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("qmsNumber", qmsNumber);

        List<QmsLsMasterDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(QmsLsMasterDto.class)
        );

        return result;
    }
}
