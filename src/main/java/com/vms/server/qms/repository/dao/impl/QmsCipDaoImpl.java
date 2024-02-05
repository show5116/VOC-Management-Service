package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.CipSearchDto;
import com.vms.server.qms.repository.dao.QMSCipDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class QmsCipDaoImpl implements QMSCipDao {


    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<CipSearchDto> getCpiSearch(CipSearchDto dto) {


        String qmsno = dto.getQmsNumber();
        String status = dto.getStatus();
        String supplier = dto.getSupplier();
        String process = dto.getProcess();
        String title = dto.getTitle();
        String fromDate = dto.getFromDate();
        String toDate = dto.getToDate();
        String user = dto.getUser();
        List<String> projectTypes = dto.getProjectTypes();
        String dept = dto.getDept();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  \n");
        sb.append("  FROM (  \n");
        sb.append("        SELECT A.QMS_NUMBER,                                                                      \n");
        sb.append("               TO_CHAR(TO_DATE(A.REG_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD')       REG_DATE,     \n");
        sb.append("               GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_PROCESS_TYPE', A.PROCESS)        PROCESS,      \n");
        sb.append("               GET_SUBCONTRACT_NAME_ONLY(A.PLANT, A.SUPPLIER)                       SUPPLIER,     \n");
        sb.append("               A.PROJECT_TITLE,                                                                   \n");
        sb.append("               (SELECT LISTAGG(GET_SYSCODE_DESC_ONLY(C.PLANT, 'QMS_PROJECT_TYPE',C.ITEM_CODE), ',') WITHIN GROUP (ORDER BY C.ITEM_CODE)  \n");
        sb.append("                  FROM QMS_SELECTED_LIST C                                                        \n");
        sb.append("                 WHERE C.PLANT       = A.PLANT                                                    \n");
        sb.append("                   AND C.SYSTEM_NAME = A.SYSTEM_NAME                                              \n");
        sb.append("                   AND C.QMS_NUMBER  = A.QMS_NUMBER                                               \n");
        sb.append("                   AND C.REVISION_NO = A.REVISION_NO)                               PROJECT_TYPE, \n");
        sb.append("               A.CLOSED_FLAG                                                        STATUS,       \n");
        sb.append("               TO_CHAR(TO_DATE(A.COMPLETE_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD')  CLOSED_DATE,  \n");
        sb.append("               GET_USERID_DESC_ONLY(A.PLANT, A.REG_USER)                            USER_NAME,    \n");
        sb.append("               GET_USER_INFO(A.PLANT, A.REG_USER, 'DEPT')                           DEPT_NAME,    \n");
        sb.append("               (SELECT D.DEPT_ID                                                                  \n");
        sb.append("                  FROM ADM_EMPLOYEE C, ADM_MY_JOB D                                                 \n");
        sb.append("                 WHERE C.EMP_CODE = D.EMP_CODE                                                    \n");
        sb.append("                   AND C.EMP_CODE = A.REG_USER) AS DEPT_ID,                                       \n");
        sb.append("               A.REVISION_NO,                                                                     \n");
        sb.append("               A.CLOSED_FLAG                                                                      \n");
        sb.append("          FROM QMS_CIP_STATUS A                                                                   \n");
        sb.append("         WHERE 1 = 1                                                                              \n");
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        if (qmsno != null && !qmsno.isEmpty()) {
            sb.append("           AND UPPER(A.QMS_NUMBER) LIKE :QMSNO  \n");
            parameters.addValue("QMSNO", "%" + qmsno.toUpperCase() + "%");
        }

        if (status != null && !status.isEmpty()) {
            sb.append("           AND A.CLOSED_FLAG = :STATUS  \n");
            parameters.addValue("STATUS", status);
        }

        if (supplier != null && !supplier.isEmpty()) {
            sb.append("           AND A.SUPPLIER = :SUPPLIER \n");
            parameters.addValue("SUPPLIER", supplier);
        }

        if (process != null && !process.isEmpty()) {
            sb.append("           AND A.PROCESS = :PROCESS      \n");
            parameters.addValue("PROCESS", process);
        }

        if (title != null && !title.isEmpty()) {
            sb.append("           AND UPPER(PROJECT_TITLE) LIKE :TITLE \n");
            parameters.addValue("TITLE", "%" + title.toUpperCase() + "%");
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            sb.append("           AND A.REG_DATE >= :from_date\n");
            parameters.addValue("from_date", fromDate);
        }

        if (toDate != null && !toDate.isEmpty()) {
            sb.append("           AND A.REG_DATE <= :to_date\n");
            parameters.addValue("to_date", toDate);
        }

        if (user != null && !user.isEmpty()) {
            sb.append("           AND A.REG_USER IN (SELECT USER_ID FROM ADM_USER_INFO WHERE UPPER(USER_NAME) LIKE :USERNAME )  \n");
            parameters.addValue("USERNAME", "%" + user.toUpperCase() + "%");
        }

        sb.append("       )       \n");
        sb.append(" WHERE 1 = 1   \n");

        if(projectTypes != null && !projectTypes.isEmpty()){
            sb.append("   AND (");

            for (int i = 0; i < projectTypes.size(); i++) {
                sb.append("         UPPER(PROJECT_TYPE) LIKE '%" + projectTypes.get(i).toUpperCase() + "%'");
                if (i != projectTypes.size() - 1)
                    sb.append(" OR\n");
            }
            sb.append(" )\n");
        }

        if (dept != null && !dept.isEmpty()) {
            sb.append("   AND UPPER(DEPT_NAME) LIKE :DEPT \n");
            parameters.addValue("DEPT", "%" + dept.toUpperCase() + "%");
        }

        sb.append(" ORDER BY QMS_NUMBER DESC \n");


        List<CipSearchDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CipSearchDto.class)
        );
        return result;
    }

    @Override
    public CipSearchDto getCpiInfo(CipSearchDto dto) {
        String plant = dto.getPlant();
        String qmsno = dto.getQmsNumber();
        String systemName = dto.getSystemName();
        String sysMType = dto.getSysMType();
        String sysSType = dto.getSysSType();
        String revNo = dto.getRevisionNo();
        List<String> projectTypes = dto.getProjectTypes();
        String dept = dto.getDept();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT A.QMS_NUMBER,\n");
        sb.append("       A.CLOSED_FLAG STATUS,\n");
        sb.append("       A.PROJECT_TITLE,\n");
        sb.append("       A.PROCESS,\n");
        sb.append("       A.SUPPLIER,\n");
        sb.append("       (SELECT LISTAGG(GET_SYSCODE_DESC_ONLY(C.PLANT, 'QMS_PROJECT_TYPE',C.ITEM_CODE), ',') WITHIN GROUP (ORDER BY C.ITEM_CODE)\n");
        sb.append("          FROM QMS_SELECTED_LIST C\n");
        sb.append("         WHERE C.PLANT = A.PLANT\n");
        sb.append("           AND C.SYSTEM_NAME = A.SYSTEM_NAME\n");
        sb.append("           AND C.QMS_NUMBER = A.QMS_NUMBER\n");
        sb.append("           AND C.REVISION_NO = A.REVISION_NO) PROJECT_TYPE,\n");
        sb.append("       A.IMPROVEMENT_ITEMS,\n");
        sb.append("       A.CURRENT_LEVEL,\n");
        sb.append("       A.TARGET_LEVEL,\n");
        sb.append("       A.IMPROVEMENT_RESULT,\n");
        sb.append("       TO_CHAR(TO_DATE(A.ISSUE_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') START_DATE,\n");
        sb.append("       TO_CHAR(TO_DATE(A.ESTIMATED_CMPL_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') CMPL_DATE,\n");
        sb.append("       TO_CHAR(TO_DATE(A.COMPLETE_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') CLOSED_DATE,\n");
        sb.append("       A.REVISION_NO\n");
        sb.append("  FROM QMS_CIP_STATUS A\n");
        sb.append(" WHERE 1 = 1\n");

        if (qmsno != null && !qmsno.isEmpty()) {
            sb.append("   AND UPPER(A.QMS_NUMBER) LIKE :QMSNO\n");
            parameters.addValue("QMSNO", "%" + qmsno.toUpperCase() + "%");
        }

        if (revNo != null && !revNo.isEmpty()) {
            sb.append("   AND UPPER(A.REVISION_NO) LIKE :REVNO\n");
            parameters.addValue("REVNO", "%" + revNo.toUpperCase() + "%");
        }

        CipSearchDto result = jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(CipSearchDto.class)
        );
        return result;
    }

    @Override
    public String getSystemCode(String plant, String itemType, String projectType) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT get_syscode_code(:plant, :itemType, :projectType) A FROM DUAL ");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("itemType", itemType)
                .addValue("projectType", projectType);

        return jdbcTemplate.queryForObject(sb.toString(), namedParameters, String.class);

    }
}
