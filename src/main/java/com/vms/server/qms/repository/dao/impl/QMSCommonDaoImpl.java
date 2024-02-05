package com.vms.server.qms.repository.dao.impl;

import com.vms.server.qms.repository.dao.QMSCommonDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QMSCommonDaoImpl implements QMSCommonDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int getQmsAttachedFileSeq(String plant, String systemName, String sysMType, String sysSType, String qmsNumber, String revisionNo) {

        StringBuilder sb = new StringBuilder();
        sb.append(" (SELECT NVL (MAX (FILE_SEQ), '0') + 1 \n ");
        sb.append("    FROM QMS_ATTACH_FILE \n ");
        sb.append("   WHERE PLANT =  :plant \n ");
        sb.append("     AND SYSTEM_NAME = :systemName \n ");
        sb.append("     AND SYSTEM_NAME_MTYPE = :sysMType \n ");
        sb.append("     AND SYSTEM_NAME_STYPE = :sysSType \n ");
        sb.append("     AND QMS_NUMBER = :qmsNumber \n ");
        sb.append("     AND REVISION_NO = :revisionNo ) \n ");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("sysMType", sysMType)
                .addValue("sysSType", sysSType)
                .addValue("qmsNumber", qmsNumber)
                .addValue("revisionNo", revisionNo);

        return jdbcTemplate.queryForObject(sb.toString(), namedParameters, Integer.class);
    }

    @Override
    public String getQmsNumber(String systemName) {

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT MAX(QMS_NUMBER) AS QMS_NUMBER FROM QMS_CIP_STATUS WHERE SUBSTR(QMS_NUMBER, 1, 3) = :systemName ");
        sb.append(" AND SUBSTR(QMS_NUMBER, 4, 2) = SUBSTR(TO_CHAR(SYSDATE, 'YYYY'), 3, 2) \n ");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("systemName", systemName);

        return jdbcTemplate.queryForObject(sb.toString(), namedParameters, String.class);
    }
}
