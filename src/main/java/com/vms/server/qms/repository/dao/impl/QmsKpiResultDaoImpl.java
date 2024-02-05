package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.QmsKpiResultSearchDto;
import com.vms.server.domain.vo.AdmDeptVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import com.vms.server.qms.repository.dao.QmsKpiResultDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class QmsKpiResultDaoImpl implements QmsKpiResultDao {


    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<AdmDeptVo> getDept(String plant, String selectDept) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT A.DEPT_NAME, A.DEPT_ID\n");
        sb.append("FROM (\n");
        sb.append("    SELECT DEPT_ID,\n");
        sb.append("        SUBSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) - 1) as BU_ID,\n");
        sb.append("        SUBSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) - 1) as BU,\n");
        sb.append("        DEPT_NAME, '' AS FUNCTION_TYPE, SYS_CONNECT_BY_PATH(DEPT_ID, '|') AS PATHE\n");
        sb.append("    FROM (SELECT * FROM ADM_DEPT WHERE DEPT_STATUS <> 'D') A\n");
        sb.append("    WHERE 1 = 1 AND CONNECT_BY_ISLEAF = '1'\n");
        sb.append("    START WITH UP_DEPT_IDX = 'ROOT'\n");
        sb.append("    CONNECT BY PRIOR DEPT_ID = UP_DEPT_IDX\n");
        sb.append("    ORDER BY DEPT_ORDER\n");
        sb.append(") A, (\n");
        sb.append("    SELECT DEPT_BU, DEPT_ID, FUNCTION_TYPE\n");
        sb.append("    FROM ADM_DEPT_MAPPING\n");
        sb.append("    WHERE PLANT = :plant \n");
        sb.append(") B\n");
        sb.append("WHERE A.DEPT_ID = B.DEPT_ID(+)\n");
        sb.append("  AND A.BU_ID = B.DEPT_BU(+)\n");
        sb.append("  AND A.BU_ID IS NOT NULL\n");

        if (selectDept != null && !selectDept.isEmpty()) {
            sb.append("  AND INSTR(A.PATHE, :selectDept , 1) > 1\n");
        }

        sb.append("ORDER BY A.DEPT_NAME\n");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("selectDept", selectDept);

        List<AdmDeptVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(AdmDeptVo.class)
        );
        return result;
    }

    @Override
    public List<Map<String, Object>> getSearch(QmsKpiResultSearchDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String docTitle = dto.getDocTitle();
        String docNo = dto.getDocNo();
        String deptBu = dto.getDeptBu();
        String dept = dto.getDept();
        String deptFunction = dto.getDeptFunction();
        String drafter = dto.getDrafter();
        String gubun = dto.getGubun();
        String process = dto.getProcess();
        List<String> regYear = dto.getRegYear();
        String subcontract = dto.getSubcontract();
        boolean myDoc = dto.isMyDoc();
        boolean isInput = dto.isInput();
        String userId = dto.getUserId();
        String userRole = dto.getUserRole();
        List<SysSystemCodeDataVo> dtCycle = dto.getCycleList();

        StringBuilder sb = new StringBuilder();

        if (isInput) {
            sb.append("SELECT * FROM ( ");
        }
        sb.append("SELECT RES.REG_YEAR, RTS.LST_QMS_NUMBER, \n")
                .append("GET_DEPT_NAME_ONLY(RES.DEPT_BU) AS DEPT_BU, \n")
                .append("GET_SYSCODE_DESC_ONLY(RES.PLANT, 'FUNCTION_TYPE', RES.FUNCTION_TYPE) AS FUNCTION_TYPE, \n")
                .append("GET_SYSCODE_DESC_ONLY(RES.PLANT, 'QMS_CAUSE_TYPE', RES.TYPE) AS TYPE, \n")
                .append("NVL(GET_SYSCODE_DESC_ONLY(RES.PLANT, 'QMS_PROCESS_TYPE', RES.PROCESS), '-') AS PROCESS, \n")
                .append("NVL(GET_SUBCONTRACT_NAME_ONLY(RES.PLANT, RES.SUBCONTRACT), '-') AS SUBCONTRACT, \n")
                .append("GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'KPI_CLASSIFICATION', MAS.CLASSIFICATION) AS CLASSIFICATION, \n")
                .append("LST.WEIGHT, \n")
                .append("MAS.DOC_NUMBER, \n")
                .append("DOC.DOC_TITLE, \n")
                .append("MAS.KPI, \n")
                .append("MAS.CALC_FORMULA, \n")
                .append("GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'QMS_KPI_UNIT', MAS.UNIT) AS UNIT, \n")
                .append("RTS.TARGET, \n");
        for (int i = 0; i < dtCycle.size(); i++) {
            String columnName = dtCycle.get(i).getCodeName();
            sb.append("RTS.RESULT_").append(columnName).append(", \n");
        }
        sb.append("GET_USERID_DESC_ONLY(RES.PLANT, RES.REG_USER) AS REG_USER, \n")
                .append("RES.REG_YEAR AS REG_YEAR_R, \n")
                .append("RES.DEPT_BU AS DEPT_BU_R, \n")
                .append("RES.FUNCTION_TYPE AS FUNCTION_TYPE_R, \n")
                .append("RES.TYPE AS TYPE_R, \n")
                .append("RES.PROCESS AS PROCESS_R, \n")
                .append("RES.SUBCONTRACT AS SUBCONTRACT_R, \n")
                .append("RES.REG_USER AS REG_USER_R, \n");
        for (int i = 0; i < dtCycle.size(); i++) {
            String columnName =dtCycle.get(i).getCodeName();
            sb.append("CASE WHEN MAS.CYCLE_").append(columnName).append(" = 'N' THEN '' ELSE \n")
                    .append("CASE WHEN TO_CHAR(SYSDATE, 'YYYYMM') >= RES.REG_YEAR || '")
                    .append( Integer.parseInt(dtCycle.get(i).getCodeGroup1())+ "00" ).append("' THEN \n")
                    .append("CASE WHEN RTS.RESULT_").append(columnName).append(" IS NOT NULL THEN '' ELSE 'Y' END \n")
                    .append("ELSE 'N' END END AS ISOVER_").append(columnName).append(", \n");
        }
        sb.append("FROM QMS_KPI_RESULT_STATUS RES \n")
                .append("JOIN QMS_KPI_RESULT_DETAIL_STATUS RTS ON RES.PLANT = RTS.PLANT AND RES.SYSTEM_NAME = RTS.SYSTEM_NAME AND RES.QMS_NUMBER = RTS.QMS_NUMBER AND RES.REVISION_NO = RTS.REVISION_NO \n")
                .append("JOIN QMS_KPI_LIST LST ON RTS.PLANT = LST.PLANT AND RTS.SYSTEM_NAME = LST.SYSTEM_NAME AND RTS.LST_QMS_NUMBER = LST.QMS_NUMBER AND RTS.LST_REVISION_NO = LST.REVISION_NO \n")
                .append("JOIN QMS_KPI_MASTER MAS ON LST.PLANT = MAS.PLANT AND LST.SYSTEM_NAME = MAS.SYSTEM_NAME AND LST.KPI_NO = MAS.KPI_NO \n")
                .append("JOIN QMS_DOCUMENT_STATUS DOC ON MAS.PLANT = DOC.PLANT AND DOC.SYSTEM_NAME = 'DOC' AND MAS.DOC_NUMBER = DOC.QMS_NUMBER \n")
                .append("WHERE RES.PLANT = :plant AND RES.SYSTEM_NAME = :systemName \n");
        sb.append("   AND RES.PLANT = RTS.PLANT\n");
        sb.append("   AND RES.SYSTEM_NAME = RTS.SYSTEM_NAME\n");
        sb.append("   AND RES.QMS_NUMBER = RTS.QMS_NUMBER\n");
        sb.append("   AND RES.REVISION_NO = RTS.REVISION_NO\n");
        sb.append("   AND RTS.PLANT = LST.PLANT\n");
        sb.append("   AND RTS.SYSTEM_NAME = LST.SYSTEM_NAME\n");
        sb.append("   AND RTS.LST_QMS_NUMBER = LST.QMS_NUMBER\n");
        sb.append("   AND RTS.LST_REVISION_NO = LST.REVISION_NO\n");
        sb.append("   AND LST.PLANT = MAS.PLANT\n");
        sb.append("   AND LST.SYSTEM_NAME = MAS.SYSTEM_NAME\n");
        sb.append("   AND LST.KPI_NO = MAS.KPI_NO\n");
        sb.append("   AND MAS.PLANT = DOC.PLANT\n");
        sb.append("   AND DOC.SYSTEM_NAME = 'DOC'\n");
        sb.append("   AND MAS.DOC_NUMBER = DOC.QMS_NUMBER\n");
        sb.append("   AND DOC.REVISION_NO = (SELECT MAX(REVISION_NO)\n");
        sb.append("                            FROM QMS_DOCUMENT_STATUS\n");
        sb.append("                           WHERE PLANT = DOC.PLANT\n");
        sb.append("                             AND SYSTEM_NAME = DOC.SYSTEM_NAME\n");
        sb.append("                             AND QMS_NUMBER = DOC.QMS_NUMBER)\n");

        if (docTitle != null && !docTitle.isEmpty())
            sb.append("   AND DOC.QMS_NUMBER = :docTitle \n");
        if (docNo != null && !docNo.isEmpty())
            sb.append("   AND DOC.QMS_NUMBER = :docNo \n");
        if (deptBu != null && !deptBu.isEmpty())
            sb.append("   AND LST.DEPT_BU = :deptBu \n");
        if (dept != null && !dept.isEmpty())
            sb.append("   AND LST.DEPT_ID = :dept \n");
        if (deptFunction != null && !deptFunction.isEmpty())
            sb.append("   AND LST.FUNCTION_TYPE = :deptFunction \n");
        if (drafter != null && !drafter.isEmpty())
            sb.append("   AND RES.REG_USER = :drafter \n");
        if (gubun != null && !gubun.isEmpty())
            sb.append("   AND MAS.TYPE = :gubun \n");
        if (process != null && !process.isEmpty())
            sb.append("   AND RES.PROCESS = :process \n");
        if (subcontract != null && !subcontract.isEmpty())
            sb.append("   AND RES.SUBCONTRACT = :subcontract \n");
        if (regYear != null && !regYear.isEmpty())
            sb.append("   AND MAS.REG_YEAR IN ( :regYear )\n");
        if (myDoc)
        {
            sb.append("    AND CASE WHEN MAS.TYPE = 'INT' THEN\n");
            sb.append("             CASE WHEN RES.DEPT_BU || RES.FUNCTION_TYPE = GET_USER_BU(RES.PLANT, :userId ) || GET_USER_FUNCTION_TYPE(RES.PLANT, :userId ) THEN 1 ELSE 0 END\n");
            sb.append("        ELSE\n");
            sb.append("             (SELECT COUNT('X')\n");
            sb.append("                FROM SYS_SYSTEM_CODE_DATA\n");
            sb.append("               WHERE PLANT = RES.PLANT\n");
            sb.append("                 AND TABLE_NAME = 'QMS_KPI_SUP_ROLE'\n");
            sb.append("                 AND CODE_NAME = :userRole )\n");
            sb.append("        END <> 0\n");
        }

        sb.append(" ORDER BY RES.REG_YEAR DESC, RES.QMS_NUMBER ASC, LST.QMS_NUMBER ASC\n");

        if(isInput)
        {
            String temp = "";
            for (int i = 0; i < dtCycle.size(); i++)
            {
                temp += (i != 0 ? " OR " : "") + "ISOVER_" + dtCycle.get(i).getCodeName() + " = 'Y'";
            }
            sb.append("       )\n");
            sb.append(" WHERE (" + temp + ")\n");
        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("docTitle", docTitle)
                .addValue("docNo", docNo)
                .addValue("deptBu", deptBu)
                .addValue("dept", dept)
                .addValue("deptFunction", deptFunction)
                .addValue("drafter", drafter)
                .addValue("gubun", gubun)
                .addValue("process", process)
                .addValue("regYear", regYear)
                .addValue("subcontract", subcontract)
                .addValue("userId", userId)
                .addValue("userRole", userRole);


        return jdbcTemplate.queryForList(sb.toString(), namedParameters);
    }
}
