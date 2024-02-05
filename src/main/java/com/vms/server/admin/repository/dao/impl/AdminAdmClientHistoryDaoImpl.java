package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminAdmClientHistoryDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.SysClientHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminAdmClientHistoryDaoImpl implements AdminAdmClientHistoryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<SysClientHistoryDto> getAdmClientHistory(SysClientHistoryDto sysClientHistoryDto) {
        String plant = sysClientHistoryDto.getPlant();
        String login = sysClientHistoryDto.getLogin();
        String startDate = sysClientHistoryDto.getStartDate();
        String endDate = sysClientHistoryDto.getEndDate();
        String useProgram = sysClientHistoryDto.getUseProgram();
        String status = sysClientHistoryDto.getStatus();
        String user = sysClientHistoryDto.getWorkingUser();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT USE_PROGRAM AS USE_PROGRAM, ");
        sb.append("       COMPUTER_NAME AS COMPUTER_NAME, ");
        sb.append("       WORKING_USER AS WORKING_USER, ");
        sb.append("       STATUS AS STATUS, ");
        sb.append("       LOGIN_TIME AS LOGIN_TIME, ");
        sb.append("       LOGOUT_TIME AS LOGOUT_TIME, ");
        sb.append("       DECODE(STATUS, '로그인 실패', '', SUBSTR(USE_TIME, INSTR(USE_TIME, ' ') +1, 2) || ':' || SUBSTR(USE_TIME, INSTR(USE_TIME, ' ') + 4, 2) || ':' || SUBSTR(USE_TIME, INSTR(USE_TIME, ' ') + 7, 2)) AS USE_TIME");
        sb.append("      , PROGRAM_VERSION AS PROGRAM_VERSION ");
        sb.append("      , IP_ADDR AS IP_ADDR ");
        sb.append("      , FAIL_COUNT AS FAIL_COUNT");
        sb.append("  FROM ( ");
        sb.append("        SELECT CASE WHEN USE_PROGRAM = 'QMS' THEN '품질관리' ELSE '기준정보' END AS USE_PROGRAM, ");
        sb.append("               COMPUTER_NAME, ");
        sb.append("               NVL(GET_USERID_DESC(:plant, WORKING_USER), WORKING_USER) AS WORKING_USER, ");
        sb.append("               CASE WHEN STATUS = 'I' THEN '로그인' WHEN STATUS = 'F' THEN '로그인 실패' ELSE '로그아웃' END AS STATUS, ");
        sb.append("               TO_CHAR(TO_DATE(DECODE(STATUS, 'F', LAST_TRANS_TIME, LOGIN_TIME), 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS') AS LOGIN_TIME, ");
        sb.append("               TO_CHAR(TO_DATE(LOGOUT_TIME, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS') AS LOGOUT_TIME, ");
        sb.append("               CASE WHEN STATUS = 'I' THEN ");
        sb.append("                SYSTIMESTAMP - TO_TIMESTAMP(LOGIN_TIME, 'YYYYMMDDHH24MISS') ");
        sb.append("               ELSE ");
        sb.append("                 TO_TIMESTAMP(LOGOUT_TIME, 'YYYYMMDDHH24MISS') - TO_TIMESTAMP(LOGIN_TIME, 'YYYYMMDDHH24MISS')");
        sb.append("               END AS USE_TIME ");
        sb.append("               , PROGRAM_VERSION ");
        sb.append("               , IP_ADDR ");
        sb.append("               , FAIL_COUNT ");
        sb.append("          FROM SYS_CLIENT_HISTORY HIS ");
        sb.append("         WHERE IN_PLANT = :plant ");
        sb.append("           AND ").append(login).append(" >= :start_dt ");
        sb.append("           AND ").append(login).append(" <= :end_dt ");

        if (useProgram != null && !useProgram.isEmpty()) {
            sb.append("   AND USE_PROGRAM = :useProgram ");
        }
        if (status != null && !status.isEmpty()) {
            sb.append("   AND STATUS = :status ");
        }
        if (user != null && !user.isEmpty()) {
            sb.append("   AND WORKING_USER LIKE '%").append(user).append("%' ");
        }
        sb.append("           ) ");
        sb.append("       ORDER BY ").append(login).append(" DESC ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("start_dt", startDate);
        parameters.addValue("end_dt", endDate);
        parameters.addValue("useProgram", useProgram);
        parameters.addValue("status", status);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysClientHistoryDto.class)
        );
    }
}
