package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminSysFileLogDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.SysFileLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminSysFileLogDaoImpl implements AdminSysFileLogDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<SysFileLogDto> getSysFileLog(SysFileLogDto sysFileLogDto) {
        String plant = sysFileLogDto.getPlant();
        String startDate = sysFileLogDto.getStartDate();
        String endDate = sysFileLogDto.getEndDate();
        String status = sysFileLogDto.getStatus();
        List<String> userId = sysFileLogDto.getUserId();
        String fileName = sysFileLogDto.getFileName();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT USER_ID AS USER_ID ");
        sb.append("       , GET_USERID_DESC_ONLY(FLOG.PLANT, FLOG.USER_ID) AS USER_NAME ");
        sb.append("       , DEPT.DEPT_NAME AS DEPT_NAME ");
        sb.append("       , DECODE(FLOG.STATUS, 'P', '미리보기', '다운로드') AS STATUS ");
        sb.append("       , FLOG.IP_ADDR AS IP_ADDR ");
        sb.append("       , FLOG.FILE_NAME AS FILE_NAME ");
        sb.append("       , TO_CHAR(TO_DATE(FLOG.TRANS_TIME, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS') AS TRANS_TIME  ");
        sb.append("       , FLOG.TRANS_TIME AS TRANS_TIME_R ");
        sb.append("  FROM SYS_FILE_LOG FLOG ");
        sb.append("       , ADM_MY_JOB MJOB ");
        sb.append("       , ADM_DEPT DEPT ");
        sb.append(" WHERE FLOG.PLANT = :plant ");
        sb.append("   AND FLOG.USER_ID = MJOB.EMP_CODE(+) ");
        sb.append("   AND MJOB.DEPT_ID = DEPT.DEPT_ID ");
        sb.append("   AND FLOG.TRANS_TIME >= :start_dt ");
        sb.append("   AND FLOG.TRANS_TIME <= :end_dt ");
        if (status != null && !status.isEmpty()) {
            sb.append(" AND FLOG.STATUS = :status ");
        }
        if (userId != null && !userId.isEmpty()) {
            sb.append(" AND FLOG.USER_ID IN (:userId) ");
        }
        if (fileName != null && !fileName.isEmpty()) {
            sb.append(" AND FLOG.FILE_NAME LIKE '%").append(fileName).append("%'");
        }
        sb.append(" ORDER BY FLOG.TRANS_TIME DESC ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("start_dt", startDate);
        parameters.addValue("end_dt", endDate);
        parameters.addValue("status", status);
        parameters.addValue("userId", userId);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysFileLogDto.class)
        );
    }
}
