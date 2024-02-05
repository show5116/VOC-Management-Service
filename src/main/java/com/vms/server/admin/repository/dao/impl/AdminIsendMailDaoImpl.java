package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminIsendMailDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.IsendmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminIsendMailDaoImpl implements AdminIsendMailDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<IsendmailDto> getIsendmail(IsendmailDto isendmailDto) {
        String plant = isendmailDto.getPlant();
        String startDate = isendmailDto.getStartDate();
        String endDate = isendmailDto.getEndDate();
        List<String> systemName = isendmailDto.getSystemName();
        List<String> createUser = isendmailDto.getCreateUser();
        List<String> sendFlag = isendmailDto.getSendFlag();
        String fromEmail = isendmailDto.getFromEmail();
        String toEmail = isendmailDto.getToEmail();
        String toCc = isendmailDto.getToCc();
        String subject = isendmailDto.getSubject();
        String message = isendmailDto.getMessage();


        StringBuilder sb = new StringBuilder();
        sb.append("SELECT MAIL.CREATE_USER AS CREATE_USER_R ");
        sb.append("       , DECODE(MAIL.CREATE_USER, 'SYSTEM', MAIL.CREATE_USER, GET_USERID_DESC_ONLY(:plant, MAIL.CREATE_USER)) AS CREATE_USER ");
        sb.append("       , DEPT.DEPT_NAME AS DEPT_ID ");
        sb.append("       , MAIL.SYSTEM_NAME AS SYSTEM_NAME ");
        sb.append("       , MAIL.SUBJECT AS SUBJECT ");
        sb.append("       , MAIL.FROM_EMAIL AS FROM_EMAIL ");
        sb.append("       , REPLACE(MAIL.TO_EMAIL, ',', CHR(13) || CHR(10)) AS TO_EMAIL ");
        sb.append("       , REPLACE(MAIL.TO_CC, ',', CHR(13) || CHR(10)) AS TO_CC ");
        sb.append("       , MAIL.MESSAGE AS MESSAGE ");
        sb.append("       , DECODE(MAIL.SEND_FLAG, 0, '전송완료', -1, '실패', 1, '전송중', 2, '전송대기') AS SEND_FLAG ");
        sb.append("       , TO_CHAR(TO_DATE(MAIL.CREATE_TIME, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS') AS CREATE_TIME ");
        sb.append("       , TO_CHAR(TO_DATE(MAIL.SEND_TIME, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS') AS SEND_TIME ");
        sb.append("  FROM ISENDMAIL MAIL ");
        sb.append("       , ADM_MY_JOB MJOB ");
        sb.append("       , ADM_DEPT DEPT ");
        sb.append(" WHERE MAIL.CREATE_USER = MJOB.EMP_CODE(+) ");
        sb.append("   AND MJOB.DEPT_ID = DEPT.DEPT_ID(+) ");
        sb.append("   AND MAIL.CREATE_TIME >= :start_dt ");
        sb.append("   AND MAIL.CREATE_TIME <= :end_dt ");

        if (systemName != null && !systemName.isEmpty()) {
            sb.append("   AND MAIL.SYSTEM_NAME IN (:systemName) ");
        }
        if (createUser != null && !createUser.isEmpty()) {
            sb.append("   AND MAIL.CREATE_USER IN (:createUser) ");
        }
        if (sendFlag != null && !sendFlag.isEmpty()) {
            sb.append("   AND MAIL.SEND_FLAG IN (:sendFlag) ");
        }
        if (fromEmail != null && !fromEmail.isEmpty()) {
            sb.append("   AND MAIL.FROM_EMAIL LIKE '%" ).append(fromEmail).append( "%' ");
        }
        if (toEmail != null && !toEmail.isEmpty()) {
            sb.append("   AND MAIL.TO_EMAIL LIKE '%" ).append(toEmail).append( "%' ");
        }
        if (toCc != null && !toCc.isEmpty()) {
            sb.append("   AND MAIL.TO_CC LIKE '%" ).append(toCc).append( "%' ");
        }
        if (subject != null && !subject.isEmpty()) {
            sb.append("   AND MAIL.SUBJECT LIKE '%" ).append(subject).append( "%' ");
        }
        if (message != null && !message.isEmpty()) {
            sb.append("   AND MAIL.MESSAGE LIKE '%" ).append(message).append( "%' ");
        }
        sb.append( " ORDER BY SEQ DESC ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("start_dt", startDate);
        parameters.addValue("end_dt", endDate);
        parameters.addValue("systemName", systemName);
        parameters.addValue("createUser", createUser);
        parameters.addValue("sendFlag", sendFlag);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(IsendmailDto.class)
        );
    }
}
