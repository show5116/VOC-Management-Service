package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.MrbManagerDto;
import com.vms.server.domain.dto.MrbSearchDto;
import com.vms.server.domain.dto.QmsApprovalRuleDto;
import com.vms.server.qms.repository.dao.QmsMrbDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QmsMrbDaoImpl implements QmsMrbDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<MrbSearchDto> getMrbSearch(MrbSearchDto dto) {
        StringBuilder sb = new StringBuilder();
        String plant=dto.getPlant();
        String mode= dto.getMode();
        String systemName= dto.getSystemName();
        String  endDate=dto.getEndDate();
        String startDate= dto.getStartDate();
        List<String> dscbCause= dto.getDscbCause();
        List<String> dscbPhen = dto.getDscbPhen();
        String userId = dto.getUserId();
        String  txtTitle=dto.getTxtTitle();
        String txtProduct =dto.getTxtProduct();
        String txtRegUser = dto.getTxtRegUser();
        String txtSupplier = dto.getTxtSupplier();
        String txtQmsNumber = dto.getTxtQmsNumber();
        List<String> issueOper = dto.getIssueOper();
        boolean chkExternal=dto.isChkExternal();
        boolean chkMyDocument =dto.isChkMyDocument();

        sb.append(" SELECT A.QMS_NUMBER ");
        sb.append("      , TO_CHAR(TO_DATE(A.REG_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') REG_DATE ");
        sb.append("      , A.ISSUE_TITLE ");
        sb.append("      , TO_CHAR(TO_DATE(A.ISSUE_TIME, 'YYYYMMDDHH24MISS'),'YYYY-MM-DD') ISSUE_TIME ");
        sb.append("      , (SELECT RTRIM(LISTAGG(DEVICE || ',') WITHIN GROUP(ORDER BY DEVICE), ',') AS DEVICE ");
        sb.append("           FROM (SELECT DISTINCT QMS_NUMBER, DEVICE, SYSTEM_NAME, REVISION_NO ");
        sb.append("                   FROM QMS_MRB_LOT_LIST ");
        sb.append("                ) ");
        sb.append("          WHERE PLANT = A.PLANT ");
        sb.append("            AND QMS_NUMBER = A.QMS_NUMBER ");
        sb.append("            AND SYSTEM_NAME = A.SYSTEM_NAME ");
        sb.append("            AND REVISION_NO = A.REVISION_NO ");
        sb.append("          GROUP BY QMS_NUMBER) AS DEVICE ");
        sb.append("      , GET_SYSCODE_DESC_ONLY(A.PLANT,'QMS_PROCESS_TYPE',A.ISSUE_PROCESS) ISSUE_PROCESS ");
        sb.append(" , (SELECT CUSTOMER_NAME ");
        sb.append("      FROM (SELECT DISTINCT CUSTOMER_NAME, CUSTOMER ");
        sb.append("              FROM ADM_CUSTOMER ");
        sb.append("             WHERE PLANT = :plant ");
        sb.append("            UNION ALL ");
        sb.append("            SELECT DISTINCT SUBCTRT_DESC CUSTOMER_NAME, SUBCTRT_CODE CUSTOMER ");
        sb.append("              FROM ADM_SUBCONTRACTOR ");
        sb.append("             WHERE PLANT = :plant  ) ");
        sb.append("     WHERE CUSTOMER = A.ISSUE_SUPPLIER) ISSUE_SUPPLIER ");
        sb.append("      , GET_SYSCODE_DESC_ONLY(A.PLANT,'QMS_PROBLEM_CATEGORY',A.FAULTY_PHENOMENON) FAULTY_PHENOMENON ");
        sb.append("      , GET_SYSCODE_DESC_ONLY(A.PLANT,'QMS_CAUSE_CATEGORY',A.FAULTY_CAUSE) FAULTY_CAUSE ");
        sb.append("      , A.EXTERNAL_FLAG ");
        sb.append("      , A.CURRENT_STEP CURRENT_STEP_CODE ");
        sb.append("      , GET_SYSCODE_DESC_ONLY(A.PLANT,'APPROVAL_TYPE', A.CURRENT_STEP) CURRENT_STEP ");
        sb.append("      , (SELECT TO_CHAR(TO_DATE(MAX(R.APPROVAL_DATE), 'YYYYMMDDHH24MISS'),'YYYY-MM-DD') ");
        sb.append("               FROM QMS_APPROVAL_RULE R ");
        sb.append("              WHERE R.PLANT = A.PLANT AND R.SYSTEM_NAME = A.SYSTEM_NAME AND R.QMS_NUMBER = A.QMS_NUMBER ");
        sb.append("                AND R.REVISION_NO = A.REVISION_NO AND APPROVAL_TYPE NOT IN '10' ");
        sb.append("                    ) APPROVAL_DATE ");
        sb.append("      , NVL(GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_CLOSE_STATUS', DECODE(CLOSED_FLAG, 'N', 'O', DECODE(A.AVAILABILITY_FLAG, 'Y', 'C', 'N/A'))), 'N/A') AS AVAILABILITY_FLAG ");
        sb.append("      , DECODE(GET_CLOSING_STATUS(A.PLANT, A.SYSTEM_NAME, A.QMS_NUMBER, A.REVISION_NO), '', ");
        sb.append("          (SELECT TO_CHAR(TO_DATE(MAX(R.APPROVAL_DATE), 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') ");
        sb.append("                   FROM QMS_APPROVAL_RULE R ");
        sb.append("                  WHERE R.PLANT = A.PLANT AND R.SYSTEM_NAME = A.SYSTEM_NAME AND R.QMS_NUMBER = A.QMS_NUMBER ");
        sb.append("                    AND R.REVISION_NO = A.REVISION_NO AND APPROVAL_TYPE NOT IN '10' ");
        sb.append("                        ), 'C', TO_CHAR(TO_DATE(B.CLOSED_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD'), '') CLOSED_DATE ");
        sb.append("      , GET_USERID_DESC_ONLY(A.PLANT, A.REG_USER) REG_USER ");
        sb.append("   FROM QMS_MRB_MASTER A ");
        sb.append("        , ( ");
        sb.append("            SELECT QMS_NUMBER, REVISION_NO, MAX(EXPAND_FIELD1) AS CLOSED_DATE ");
        sb.append("             FROM QMS_ATTACH_FILE ");
        sb.append("            WHERE 1 = 1 ");
        sb.append("               AND PLANT = :plant ");
        sb.append("               AND SYSTEM_NAME = :systemName ");
        sb.append("               AND SYSTEM_NAME_MTYPE = 'VALIDATION' ");
        sb.append("             GROUP BY QMS_NUMBER, REVISION_NO ");
        sb.append("        ) B ");
        sb.append("  WHERE A.PLANT = :plant ");
        sb.append("    AND A.SYSTEM_NAME = :systemName ");
        sb.append("    AND A.QMS_NUMBER = B.QMS_NUMBER(+) ");
        sb.append("    AND A.REVISION_NO = B.REVISION_NO(+) ");

        if ("10".equals(mode)) {
            if (chkMyDocument) {
                sb.append("    AND A.REG_USER = :userId    ");
            }
            sb.append("    AND A.CURRENT_STEP != '70'                     ");
            sb.append("    AND A.CURRENT_STEP != '100'                    ");

        } else if ("60".equals(mode)) {
            sb.append("      AND A.QMS_NUMBER IN (SELECT QMS_NUMBER ");
            sb.append("                              FROM QMS_APPROVAL_RULE Q ");
            sb.append("                             WHERE 1=1 ");
            sb.append("                               AND Q.PLANT = A.PLANT ");
            sb.append("                               AND Q.SYSTEM_NAME = A.SYSTEM_NAME ");
            sb.append("                               AND Q.APPROVAL_TYPE NOT IN ('10','200','900') ");
            if (chkMyDocument) {
                sb.append("                               AND Q.USER_ID = :userId ");
            }
            sb.append("                               AND Q.QMS_NUMBER NOT IN (SELECT QMS_NUMBER ");
            sb.append("                                                          FROM QMS_APPROVAL_RULE A1 ");
            sb.append("                                                         WHERE 1=1 ");
            sb.append("                                                           AND (APPROVAL_FLAG = 'N' OR APPROVAL_FLAG = 'R') ");
            sb.append("                                                           AND A1.PLANT = Q.PLANT ");
            sb.append("                                                           AND A1.SYSTEM_NAME = Q.SYSTEM_NAME ");
            sb.append("                                                           AND A1.QMS_NUMBER = Q.QMS_NUMBER ");
            sb.append("                                                           AND A1.REVISION_NO = Q.REVISION_NO ");
            sb.append("                                                           AND A1.APPROVAL_TYPE NOT IN ('10','200','900') ");
            sb.append("                                                           AND A1.APPROVAL_TYPE < (SELECT NVL(MIN(APPROVAL_TYPE), '9999') ");
            sb.append("                                                                                    FROM QMS_APPROVAL_RULE B1 ");
            sb.append("                                                                                   WHERE 1=1 ");
            sb.append("                                                                                     AND B1.PLANT = A1.PLANT ");
            sb.append("                                                                                     AND B1.SYSTEM_NAME = A1.SYSTEM_NAME ");
            sb.append("                                                                                     AND B1.QMS_NUMBER = A1.QMS_NUMBER ");
            sb.append("                                                                                     AND B1.REVISION_NO = A1.REVISION_NO ");
            sb.append("                                                                                     AND B1.APPROVAL_FLAG = 'N' ");
            sb.append("                                                                                     AND B1.APPROVAL_TYPE NOT IN ('10','200','900') ");
            if (chkMyDocument) {
                sb.append("                                                                                     AND B1.USER_ID = :userId");
            }
            sb.append("                                                                                  ) ");
            sb.append("                                                        ) ");
            sb.append("                            GROUP BY QMS_NUMBER ");
            sb.append("                            ) ");
            sb.append("    AND A.CURRENT_STEP NOT IN ('70') ");
            sb.append("    AND A.CURRENT_STEP NOT IN ('00') ");

        } else {
            if (chkMyDocument) {
                sb.append("    AND A.REG_USER = :userId     ");
            }

            if ("70".equals(mode)) {
                sb.append("    AND A.CURRENT_STEP =:mode");
            }
        }

        if (txtTitle != null && !txtTitle.isEmpty()) {
            sb.append("    AND UPPER(A.ISSUE_TITLE) LIKE '%' || UPPER(:txtTitle) || '%'     ");
        }

        if (!txtProduct.isEmpty()) {
            sb.append("    AND (SELECT RTRIM(LISTAGG(DEVICE || ',') WITHIN GROUP(ORDER BY DEVICE), ',') AS DEVICE ");
            sb.append("           FROM ( ");
            sb.append("                  SELECT DISTINCT QMS_NUMBER, DEVICE, SYSTEM_NAME, REVISION_NO ");
            sb.append("                    FROM QMS_MRB_LOT_LIST ");
            sb.append("                ) ");
            sb.append("          WHERE PLANT = A.PLANT ");
            sb.append("            AND QMS_NUMBER = A.QMS_NUMBER ");
            sb.append("            AND SYSTEM_NAME = A.SYSTEM_NAME ");
            sb.append("            AND REVISION_NO = A.REVISION_NO ");
            sb.append("          GROUP BY QMS_NUMBER) LIKE '%' || UPPER(:txtProduct) || '%'    ");
        }

        if (txtRegUser != null &&  !txtRegUser.isEmpty()) {
            sb.append("    AND A.REG_USER IN (SELECT EMP_CODE FROM ADM_EMPLOYEE WHERE UPPER(EMP_NAME) LIKE '%' || UPPER(:txtRegUser) || '%')    ");
        }

        if (txtSupplier != null && !txtSupplier.isEmpty()) {
            sb.append("    AND (A.ISSUE_SUPPLIER IN (SELECT SUBCTRT_CODE FROM ADM_SUBCONTRACTOR WHERE UPPER(SUBCTRT_DESC) LIKE '%' || UPPER(:txtSupplier)  || '%') ");
            sb.append("        OR A.ISSUE_SUPPLIER IN (SELECT CUSTOMER FROM ADM_CUSTOMER WHERE UPPER(CUSTOMER_NAME) LIKE '%' || UPPER(:txtSupplier) || '%'))  ");
        }

        if (txtQmsNumber != null &&  !txtQmsNumber.isEmpty()) {
            sb.append("    AND UPPER(A.QMS_NUMBER) LIKE '%' || UPPER(:txtQmsNumber) || '%'     ");
        }

        if (dscbPhen != null && !dscbPhen.isEmpty()) {
            sb.append("    AND A.FAULTY_PHENOMENON IN ( :dscbPhen )    ");
        }

        if (dscbCause != null &&  !dscbCause.isEmpty()) {
            sb.append("    AND A.FAULTY_CAUSE IN ( :dscbCause )    ");
        }

        if (startDate != null &&  !startDate.isEmpty()) {
            sb.append("    AND A.REG_DATE BETWEEN :startDate AND :endDate   ");
        }

        if (chkExternal) {
            sb.append("    AND A.EXTERNAL_FLAG = 'Y'    ");
        }

        if (issueOper != null &&  !issueOper.isEmpty()) {
            sb.append("    AND A.ISSUE_PROCESS IN (:issueOper )");
        }

// 쿼리 정렬 부분
        sb.append("  ORDER BY A.QMS_NUMBER DESC, A.REG_DATE DESC    ");


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("userId", userId);
        parameters.addValue("mode", mode);
        parameters.addValue("txtTitle", txtTitle);
        parameters.addValue("txtProduct", txtProduct);
        parameters.addValue("txtRegUser", txtRegUser);
        parameters.addValue("txtSupplier", txtSupplier);
        parameters.addValue("txtQmsNumber", txtQmsNumber);
        parameters.addValue("dscbPhen", dscbPhen);
        parameters.addValue("dscbCause", dscbCause);
        parameters.addValue("endDate", endDate);
        parameters.addValue("startDate", startDate);
        parameters.addValue("issueOper", issueOper);

        List<MrbSearchDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(MrbSearchDto.class)
        );

        return result;
    }

    @Override
    public List<MrbManagerDto> getViewSelect(MrbManagerDto dto) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A.QMS_NUMBER, ");
        sb.append("       A.REVISION_NO, ");
        sb.append("       A.ISSUE_TITLE, ");
        sb.append("       A.DEVICE, ");
        sb.append("       TO_CHAR(TO_DATE(A.ISSUE_TIME,'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') ISSUE_TIME, ");
        sb.append("       A.ISSUE_PROCESS, ");
        sb.append("       A.ISSUE_SUPPLIER, ");
        sb.append("       A.PROBLEM_DISPOSIOTION, ");
        sb.append("       A.ROOT_CAUSE, ");
        sb.append("       A.FAULTY_PHENOMENON, ");
        sb.append("       A.FAULTY_CAUSE, ");
        sb.append("       A.LOT_DISPOSIOTION, ");
        sb.append("       A.EXTERNAL_FLAG, ");
        sb.append("       A.AVAILABILITY_FLAG, ");
        sb.append("       A.CLOSED_FLAG, ");
        sb.append("       TO_CHAR(TO_DATE(A.REG_DATE,'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') REG_DATE, ");
        sb.append("       A.REG_USER, ");
        sb.append("       TO_CHAR(TO_DATE(A.CLOSED_DATE,'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') CLOSED_DATE, ");
        sb.append("       A.CLOSED_USER, ");
        sb.append("       A.CURRENT_STEP, ");
        sb.append("       B.EMP_NAME, ");
        sb.append("       D.DEPT_NAME, ");
        sb.append("       A.UNNECESSARY_REASON ");
        sb.append("FROM QMS_MRB_MASTER A, ADM_EMPLOYEE B, ADM_MY_JOB C, ADM_DEPT D ");
        sb.append("WHERE ROWNUM = 1 ");
        sb.append("  AND A.REG_USER = B.EMP_CODE ");
        sb.append("  AND B.EMP_CODE = C.EMP_CODE ");
        sb.append("  AND C.DEPT_ID = D.DEPT_ID ");
        sb.append("  AND A.PLANT = :plant ");
        sb.append("  AND A.SYSTEM_NAME = :systemName ");
        sb.append("  AND A.QMS_NUMBER = :qmsNumber ");
        sb.append("  AND A.REVISION_NO = :revisionNo ");


        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", dto.getPlant());
        parameters.addValue("systemName", dto.getSystemName());
        parameters.addValue("qmsNumber", dto.getQmsNumber());
        parameters.addValue("revisionNo", dto.getRevisionNo());


        List<MrbManagerDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(MrbManagerDto.class)
        );

        return result;
    }

    @Override
    public List<QmsApprovalRuleDto> getApprovalList(String plant, String systemName, String qmsNumber, String revisionNo) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT F.CODE_GROUP1 AS role, ");
        sb.append("       A.USER_ID userid, ");
        sb.append("       D.DEPT_NAME department, ");
        sb.append("       E.POS_NAME description, ");
        sb.append("       B.EMP_NAME username, ");
        sb.append("       GET_APPROVAL_STATUS(A.PLANT, A.APPROVAL_TYPE, DECODE(A.APPROVAL_TYPE,'900','Y',A.APPROVAL_FLAG)) status, ");
        sb.append("       TO_CHAR(TO_DATE(A.APPROVAL_DATE, 'YYYYMMDDHH24MISS'),'YYYY-MM-DD HH24:MI') \"date\", ");
        sb.append("       A.COMMENTS \"comment\", ");
        sb.append("       A.APPROVAL_TYPE roleId, ");
        sb.append("       DECODE(A.APPROVAL_TYPE,'900','Y',A.APPROVAL_FLAG) status_r, ");
        sb.append("FROM QMS_APPROVAL_RULE A, ");
        sb.append("     ADM_EMPLOYEE B, ");
        sb.append("     ADM_MY_JOB C, ");
        sb.append("     ADM_DEPT D, ");
        sb.append("     ADM_POSITION E, ");
        sb.append("     SYS_SYSTEM_CODE_DATA F ");
        sb.append("WHERE 1 = 1 ");
        sb.append("  AND A.USER_ID = B.EMP_CODE ");
        sb.append("  AND B.EMP_CODE = C.EMP_CODE ");
        sb.append("  AND C.DEPT_ID = D.DEPT_ID ");
        sb.append("  AND B.POS_ID = E.POS_ID ");
        sb.append("  AND A.PLANT = :plant ");
        sb.append("  AND A.SYSTEM_NAME = :systemName ");
        sb.append("  AND A.QMS_NUMBER = :qmsNumber ");
        sb.append("  AND A.REVISION_NO = :revisionNo ");
        sb.append("  AND A.PLANT = F.PLANT ");
        sb.append("  AND A.APPROVAL_TYPE = F.CODE_NAME ");
        sb.append("  AND F.TABLE_NAME = 'APPROVAL_TYPE' ");
        sb.append("ORDER BY TO_NUMBER(A.APPROVAL_TYPE), A.APPROVAL_DATE ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant",plant);
        parameters.addValue("systemName",systemName);
        parameters.addValue("qmsNumber",qmsNumber);
        parameters.addValue("revisionNo", revisionNo);


        List<QmsApprovalRuleDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(QmsApprovalRuleDto.class)
        );

        return result;
    }
}
