package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.LSSearchDto;
import com.vms.server.qms.repository.dao.LSSearchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class LSSearcDaoImpl implements LSSearchDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<LSSearchDto> searchLsData(LSSearchDto dto) {

         String plant = dto.getPlant();
         String systemName = dto.getSystemName();
         String title = dto.getTitle();
         String isNumber = dto.getIsNumber();
         String division = dto.getDivision();
         String department = dto.getDepartment();
         String issue = dto.getIssue();
         String process = dto.getProcess();
         String supplier = dto.getSupplier();
         String cause = dto.getCause();
         String product = dto.getProduct();
         String startDate = dto.getStartDate();
         String endDate = dto.getEndDate();
         String status = dto.getStatus();
         String userId = dto.getUserId();
         boolean chkMyDoc = dto.isChkMyDoc();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT A.QMS_NUMBER\n");
        sb.append("       , TO_CHAR(TO_DATE(A.REG_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') AS REG_DATE\n");
        sb.append("       , A.ISSUE_TITLE\n");
        sb.append("       , (SELECT GET_DEPT_NAME_ONLY(DEPT_BU) FROM ADM_DEPT_MAPPING WHERE PLANT = A.PLANT AND DEPT_ID = A.DEPARTMENT) AS DEPT_BU\n");
        sb.append("       , GET_DEPT_NAME_ONLY(A.DEPARTMENT)AS DEPT_NAME\n");
// 2023.11.25 KYT QMS WEB 전환 대비 WM_CONCAT 제거
// sb.append("       , (SELECT WM_CONCAT(ITEM_CODE) DEVICE FROM QMS_SELECTED_LIST WHERE QMS_NUMBER = A.QMS_NUMBER AND ITEM_TYPE = 'DEVICE') AS PRODUCT\n");
        sb.append("       , (SELECT LISTAGG(ITEM_CODE, ',') WITHIN GROUP (ORDER BY ITEM_CODE) DEVICE FROM QMS_SELECTED_LIST WHERE QMS_NUMBER = A.QMS_NUMBER AND ITEM_TYPE = 'DEVICE') AS PRODUCT\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_LS_FAULTY_STEP', A.DIVISION) AS FAULTY_STEP\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_LS_FAULTY_TYPE', A.ISSUE_CATEGORY) AS ISSUE_CATEGORY\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_CAUSE_TYPE', A.PROCESS) AS PROCESS\n");
        sb.append("       , GET_SUBCONTRACT_NAME_ONLY(A.PLANT, A.SUPPLIER) AS SUPPLIER\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_LS_CAUSE_TYPE', A.CAUSE) AS CAUSE\n");
        sb.append("       , A.ISSUE_SUMMARY\n");
        sb.append("       , A.CAUSE_COMMENT\n");
        sb.append("       , A.MEASURES\n");
        sb.append("       , B.CHECK_LIST\n");
// 2023.11.25 KYT QMS WEB 전환 대비 WM_CONCAT 제거
// sb.append("       , (SELECT WM_CONCAT(GET_SYSCODE_DESC_ONLY(C.PLANT, 'QMS_PROCESS_TYPE', C.PROCESS))\n");
        sb.append("       , (SELECT LISTAGG(GET_SYSCODE_DESC_ONLY(C.PLANT, 'QMS_PROCESS_TYPE', C.PROCESS), ',') WITHIN GROUP (ORDER BY C.PROCESS)\n");
        sb.append("            FROM QMS_LS_APPLY_RANGE C\n");
        sb.append("           WHERE C.PLANT = B.PLANT\n");
        sb.append("             AND C.SYSTEM_NAME = B.SYSTEM_NAME\n");
        sb.append("             AND C.QMS_NUMBER = B.QMS_NUMBER\n");
        sb.append("             AND C.REVISION_NO = B.REVISION_NO\n");
        sb.append("             AND C.SEQ = B.SEQ) AS APPLY_RANGE\n");
        sb.append("       , B.KEY_WORD\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(B.PLANT, 'FUNCTION_TYPE', B.FUNCTION_TYPE) AS FUNCTION_TYPE\n");
        sb.append("       , B.REMARK\n");
        sb.append("       , GET_USERID_DESC_ONLY(A.PLANT, A.REG_USER) AS REG_USER\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(A.PLANT, 'QMS_CLOSING_FLAG', A.CLOSED_FLAG) AS STATUS\n");
        sb.append("       , A.CLOSED_FLAG\n");
        sb.append("  FROM QMS_LS_MASTER A\n");
        sb.append("       , QMS_LS_DETAIL B\n");
        sb.append(" WHERE A.PLANT = :plant \n");
        sb.append("   AND A.SYSTEM_NAME = :systemName \n");
        sb.append("   AND A.PLANT = B.PLANT(+)\n");
        sb.append("   AND A.SYSTEM_NAME = B.SYSTEM_NAME(+)\n");
        sb.append("   AND A.QMS_NUMBER = B.QMS_NUMBER(+)\n");
        sb.append("   AND A.REVISION_NO = B.REVISION_NO(+)\n");

        if (isNumber != null && !isNumber.isEmpty()) {
            sb.append("   AND UPPER(A.QMS_NUMBER) LIKE UPPER('%' || :isNumber  || '%')\n");
        }
        if (title != null && !title.isEmpty()) {
            sb.append("   AND UPPER(A.ISSUE_TITLE) LIKE UPPER('%' || :title  || '%')\n");
        }
        if (product != null && !product.isEmpty()) {
            sb.append("    AND (A.QMS_NUMBER IN (SELECT QMS_NUMBER\n");
            sb.append("                            FROM QMS_SELECTED_LIST\n");
            sb.append("                           WHERE UPPER(ITEM_CODE) LIKE UPPER('%' || :product  || '%')\n");
            sb.append("                             AND ITEM_TYPE = 'DEVICE'))\n");
        }
        if (division != null && !division.isEmpty()) {
            sb.append("   AND A.DIVISION = :division \n");
        }
        if (department != null && !department.isEmpty()) {
            sb.append("   AND A.DEPARTMENT = :department \n");
        }
        if (issue != null &&!issue.isEmpty()) {
            sb.append("   AND A.ISSUE_CATEGORY = :issue \n");
        }
        if (process != null &&!process.isEmpty()) {
            sb.append("   AND A.PROCESS = :process \n");
        }
        if (supplier != null &&!supplier.isEmpty()) {
            sb.append("   AND A.SUPPLIER = :supplier \n");
        }
        if (cause != null &&!cause.isEmpty()) {
            sb.append("   AND A.CAUSE = :cause \n");
        }
        if (startDate != null &&!startDate.isEmpty()) {
            sb.append("   AND A.REG_DATE BETWEEN :startDate AND :endDate \n");
        }
        if (status != null &&!status.isEmpty()) {
            sb.append("   AND A.CLOSED_FLAG = :status \n");
        }
        if (chkMyDoc) {
            sb.append("   AND A.REG_USER = :userId \n");
        }
        sb.append(" ORDER BY A.QMS_NUMBER DESC, B.SEQ ASC\n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("systemName", systemName);
        parameters.addValue("isNumber", isNumber);
        parameters.addValue("title", title);
        parameters.addValue("product", product);
        parameters.addValue("division", division);
        parameters.addValue("department", department);
        parameters.addValue("issue", issue);
        parameters.addValue("process", process);
        parameters.addValue("supplier", supplier);
        parameters.addValue("cause", cause);
        parameters.addValue("startDate", startDate);
        parameters.addValue("endDate", endDate);
        parameters.addValue("status", status);
        parameters.addValue("userId", userId);


        List<LSSearchDto> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(LSSearchDto.class)
        );

        return result;
    }
}
