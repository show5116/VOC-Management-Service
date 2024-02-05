package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminCommonDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminCommonDaoImpl implements AdminCommonDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public CommonUserInfoDto getBaseUserInfo(String plant, String userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT							 \n ");
        sb.append("	A.EMP_NAME as empName, \n ");
        sb.append("	A.EMP_CODE as empCode , \n ");
        sb.append("	B.DEPT_ID as deptId , \n ");
        sb.append("	C.DEPT_NAME as deptName, \n ");
        sb.append("	E.POS_NAME AS OFC_NAME, \n ");
        sb.append("	A.EMAIL as email \n ");
        sb.append("FROM \n ");
        sb.append("	ADM_EMPLOYEE A, \n ");
        sb.append("	ADM_MY_JOB B, \n ");
        sb.append("	ADM_DEPT C, \n ");
        sb.append("	ADM_USER_INFO D, \n ");
        sb.append("	ADM_POSITION E \n ");
        sb.append("WHERE \n ");
        sb.append("	A.EMP_CODE = D.USER_ID \n ");
        sb.append("	AND A.EMP_CODE = B.EMP_CODE \n ");
        sb.append("	AND B.DEPT_ID = C.DEPT_ID \n ");
        sb.append("	AND A.POS_ID = E.POS_ID(+) \n ");
        sb.append("	AND D.PLANT = :plant \n ");
        sb.append("	AND D.USER_ID = :userId \n ");


        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("userId", userId);
        parameters.addValue("plant", plant);

        CommonUserInfoDto result = jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CommonUserInfoDto.class)
        );

        return result;
    }

    @Override
    public List<CommonUserInfoDto> getUserBaseInfoMulti(String plant, List<String> userIds) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT A.EMP_NAME, A.EMP_CODE,											 \n ");
        sb.append("        B.DEPT_ID, C.DEPT_NAME, \n ");
        sb.append("        E.POS_NAME AS OFC_NAME, A.EMAIL, \n ");
        sb.append("        D.USER_ID, A.EMP_NAME || '<' || A.EMAIL || '>' AS FORMAT_EMAIL \n ");
        sb.append("   FROM ADM_EMPLOYEE A, ADM_MY_JOB B, \n ");
        sb.append("        ADM_DEPT C, ADM_USER_INFO D, \n ");
        sb.append("        ADM_POSITION E \n ");
        sb.append("  WHERE A.EMP_CODE = D.USER_ID \n ");
        sb.append("    AND A.EMP_CODE = B.EMP_CODE \n ");
        sb.append("    AND B.DEPT_ID = C.DEPT_ID \n ");
        sb.append("    AND A.POS_ID = E.POS_ID \n ");
        sb.append("    AND D.PLANT = :plant \n ");
        sb.append("    AND D.USER_ID IN (:userIds ) \n ");
        sb.append("  ORDER BY A.EMP_NAME \n ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("userIds", userIds);
        parameters.addValue("plant", plant);

        List<CommonUserInfoDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CommonUserInfoDto.class)
        );

        return result;
    }

    @Override
    public List<AdmUserInfoDto> getUserBaseInfoMultiName(String plant, List<String> userNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A.EMP_NAME, A.EMP_CODE, \n");
        sb.append("       B.DEPT_ID, C.DEPT_NAME, \n");
        sb.append("       E.POS_NAME AS OFC_NAME, A.EMAIL, \n");
        sb.append("       D.USER_ID, A.EMP_NAME || '<' || A.EMAIL || '>' AS FORMAT_EMAIL \n");
        sb.append("  FROM ADM_EMPLOYEE A, ADM_MY_JOB B, \n");
        sb.append("       ADM_DEPT C, ADM_USER_INFO D, \n");
        sb.append("       ADM_POSITION E \n");
        sb.append(" WHERE A.EMP_CODE = D.USER_ID \n");
        sb.append("   AND A.EMP_CODE = B.EMP_CODE \n");
        sb.append("   AND B.DEPT_ID = C.DEPT_ID \n");
        sb.append("   AND A.POS_ID = E.POS_ID \n");
        sb.append("   AND D.PLANT = :plant \n");
        sb.append("   AND A.EMP_NAME IN ( :userNames ) \n");
        sb.append(" ORDER BY A.EMP_NAME \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("userNames", userNames);
        parameters.addValue("plant", plant);

        List<AdmUserInfoDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmUserInfoDto.class)
        );

        return result;
    }

    @Override
    public List<AdmDeptDto> getDeptLeader(String deptId) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT REPLACE(SUBSTR(PATH_EMP, DECODE(INSTR(PATH_EMP, 'SM', -1), 0, INSTR(PATH_EMP, 'IN', -1), INSTR(PATH_EMP, 'SM', -1)), 9), '-', '') AS DEPT_LEADER \n");
        sb.append("  FROM (SELECT DEPT.DEPT_ID, DEPT.DEPT_NAME, DEPT.DEPT_ORDER, DEPT.UP_DEPT_IDX, MJOB.OFC_ID, MJOB.EMP_CODE \n");
        sb.append("               , SYS_CONNECT_BY_PATH(MJOB.OFC_ID, '-') AS PATH_OFC \n");
        sb.append("               , SYS_CONNECT_BY_PATH(MJOB.EMP_CODE, '-') AS PATH_EMP \n");
        sb.append("          FROM ADM_DEPT DEPT \n");
        sb.append("               , (SELECT DEPT_ID, OFC_ID, EMP_CODE \n");
        sb.append("                    FROM ADM_MY_JOB A \n");
        sb.append("                   WHERE 1 = 1 \n");
        sb.append("                     AND OFC_ID IS NOT NULL \n");
        sb.append("                     AND DEPT_ID || OFC_ID IN ( \n");
        sb.append("                                               SELECT DEPT_ID || MIN(OFC_ID) \n");
        sb.append("                                                 FROM ADM_MY_JOB \n");
        sb.append("                                                WHERE 1 = 1 \n");
        sb.append("                                                  AND OFC_ID IS NOT NULL \n");
        sb.append("                                                  AND DEPT_ID = A.DEPT_ID \n");
        sb.append("                                                GROUP BY DEPT_ID \n");
        sb.append("                                             )) MJOB \n");
        sb.append("         WHERE DEPT_STATUS <> 'D' \n");
        sb.append("           AND DEPT.DEPT_ID = MJOB.DEPT_ID(+) \n");
        sb.append("         START WITH UP_DEPT_IDX = 'ROOT' \n");
        sb.append("         CONNECT BY PRIOR DEPT.DEPT_ID = DEPT.UP_DEPT_IDX \n");
        sb.append("         ORDER BY DEPT.DEPT_ORDER \n");
        sb.append("       ) DEPT \n");
        sb.append(" WHERE DEPT.DEPT_ID = :deptId \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("deptId", deptId);


        List<AdmDeptDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmDeptDto.class)
        );

        return result;

    }

    @Override
    public List<AdmUserInfoDto> getRegistInfo(String plant, String systemName, String qmsNumber, String revisionNo) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A.EMP_NAME, A.EMP_CODE, \n");
        sb.append("       B.DEPT_ID, C.DEPT_NAME, \n");
        sb.append("       E.POS_NAME AS OFC_NAME \n");
        sb.append("  FROM ADM_EMPLOYEE A, \n");
        sb.append("       ADM_MY_JOB B, \n");
        sb.append("       ADM_DEPT C, \n");
        sb.append("       ADM_USER_INFO D, \n");
        sb.append("       ADM_POSITION E, \n");
        sb.append("       (SELECT USER_ID \n");
        sb.append("          FROM QMS_APPROVAL_RULE \n");
        sb.append("         WHERE PLANT = :plant \n");
        sb.append("           AND SYSTEM_NAME = :systemName \n");
        sb.append("           AND QMS_NUMBER = :qmsNumber \n");
        sb.append("           AND REVISION_NO = :revisionNo \n");
        sb.append("           AND APPROVAL_TYPE = '10' ) F \n");
        sb.append(" WHERE A.EMP_CODE = D.USER_ID \n");
        sb.append("   AND A.EMP_CODE = B.EMP_CODE \n");
        sb.append("   AND B.DEPT_ID = C.DEPT_ID \n");
        sb.append("   AND A.POS_ID = E.POS_ID \n");
        sb.append("   AND A.EMP_CODE = F.USER_ID \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("qmsNumber", qmsNumber);
        parameters.addValue("revisionNo", revisionNo);

        List<AdmUserInfoDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmUserInfoDto.class)
        );

        return result;
    }

    @Override
    public List<CommonRoleDto> getUserRoleInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId) {


        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CHECK_APPROVAL_ROLE(:plant, :systemName, :qmsNumber, :revisionNo, :userId) ROLE, \n");
        sb.append("       CHECK_APPROVAL_FLAG(:plant, :systemName, :qmsNumber, :revisionNo, :userId) ROLE_FLAG, \n");
        sb.append("       CHECK_APPROVAL_USE_FLAG(:plant, :systemName, :qmsNumber, :revisionNo, :userId) USE_FLAG \n");
        sb.append("  FROM DUAL \n");


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("qmsNumber", qmsNumber);
        parameters.addValue("revisionNo", revisionNo);
        parameters.addValue("userId", userId);

        List<CommonRoleDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CommonRoleDto.class)
        );
        return result;
    }

    @Override
    public CommonUserInfoDto getUserInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A.EMP_NAME, A.EMP_CODE, \n");
        sb.append("       B.DEPT_ID, C.DEPT_NAME, \n");
        sb.append("       E.POS_NAME AS OFC_NAME, \n");
        sb.append("        CHECK_APPROVAL_ROLE(:plant, :systemName, :qmsNumber, :revisionNo, :userId) ROLE, \n");
        sb.append("       CHECK_APPROVAL_FLAG(:plant, :systemName, :qmsNumber, :revisionNo, :userId) ROLE_FLAG, \n");
        sb.append("       CHECK_APPROVAL_USE_FLAG(:plant, :systemName, :qmsNumber, :revisionNo, :userId) USE_FLAG \n");
        sb.append("  FROM ADM_EMPLOYEE A, \n");
        sb.append("       ADM_MY_JOB B, \n");
        sb.append("       ADM_DEPT C, \n");
        sb.append("       ADM_USER_INFO D, \n");
        sb.append("       ADM_POSITION E \n");
        sb.append(" WHERE A.EMP_CODE = D.USER_ID \n");
        sb.append("   AND A.EMP_CODE = B.EMP_CODE \n");
        sb.append("   AND B.DEPT_ID = C.DEPT_ID \n");
        sb.append("   AND A.POS_ID = E.POS_ID \n");
        sb.append("   AND D.PLANT = :plant \n");
        sb.append("   AND D.USER_ID = :userId \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("qmsNumber", qmsNumber);
        parameters.addValue("revisionNo", revisionNo);
        parameters.addValue("userId", userId);

        CommonUserInfoDto result = jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CommonUserInfoDto.class)
        );
        return result;

    }

    @Override
    public AdmUserInfoDto getDefaultUserInfo(String plant, String userId) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A.EMP_NAME, A.EMP_CODE, B.DEPT_ID , C.DEPT_NAME, E.POS_NAME AS OFC_NAME \n");
        sb.append("  FROM ADM_EMPLOYEE A, ADM_MY_JOB B, ADM_DEPT C, ADM_USER_INFO D, ADM_POSITION E \n");
        sb.append(" WHERE A.EMP_CODE = D.USER_ID \n");
        sb.append("   AND A.EMP_CODE = B.EMP_CODE \n");
        sb.append("   AND B.DEPT_ID  = C.DEPT_ID \n");
        sb.append("   AND A.POS_ID   = E.POS_ID \n");
        sb.append("   AND D.PLANT    = :plant \n");
        sb.append("   AND D.USER_ID  = :userId \n");
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("userId", userId);

        AdmUserInfoDto result = jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmUserInfoDto.class)
        );
        return result;
    }

    @Override
    public List<CustomerMappingDto> getCustomerSupplierList(String plant) {

        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT DISTINCT * \n");
        sb.append("     FROM (SELECT CUSTOMER_NAME, CUSTOMER \n");
        sb.append("             FROM ADM_CUSTOMER \n");
        sb.append("            WHERE PLANT = :plant \n");
        sb.append("           UNION ALL \n");
        sb.append("           SELECT SUBCTRT_DESC CUSTOMER_NAME, SUBCTRT_CODE CUSTOMER \n");
        sb.append("             FROM ADM_SUBCONTRACTOR \n");
        sb.append("            WHERE PLANT = :plant \n");
        sb.append("              AND (ISPRB = 'Y' OR ISFT = 'Y' OR ISWFT = 'Y')) \n");
        sb.append(" ORDER BY CUSTOMER_NAME ASC \n");


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);


        List<CustomerMappingDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CustomerMappingDto.class)
        );

        return result;
    }

    @Override
    public Integer getTextColumnSize(TableInfoDto dto) {

        String sql = "SELECT  DATA_LENGTH FROM USER_TAB_COLUMNS WHERE TABLE_NAME = :tableName AND COLUMN_NAME=:columnName";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("tableName",dto.getTableName() );
        parameters.addValue("columnName",dto.getColumnName());

        return  jdbcTemplate.queryForObject(
                sql,
                parameters,
                Integer.class);
    }


}
