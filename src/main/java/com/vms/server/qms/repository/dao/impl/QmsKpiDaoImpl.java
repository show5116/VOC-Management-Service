package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.dto.KpiManagerDto;
import com.vms.server.domain.dto.QmsKpiSearchDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.MailVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import com.vms.server.qms.repository.dao.QmsKpiDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Transactional
public class QmsKpiDaoImpl implements QmsKpiDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<AdmDeptDto> getBu(String plant, String selectDept) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT A.BU, A.BU_ID ");
        sb.append("FROM ( ");
        sb.append("    SELECT DEPT_ID, ");
        sb.append("           SUBSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) - 1) AS BU_ID, ");
        sb.append("           SUBSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) - 1) AS BU, ");
        sb.append("           DEPT_NAME, ");
        sb.append("           '' AS FUNCTION_TYPE, ");
        sb.append("           SYS_CONNECT_BY_PATH(DEPT_ID, '|') AS PATH ");
        sb.append("    FROM (SELECT * FROM ADM_DEPT WHERE DEPT_STATUS <> 'D') A ");
        sb.append("    WHERE 1 = 1 ");
        sb.append("      AND CONNECT_BY_ISLEAF = '1' ");
        sb.append("    START WITH UP_DEPT_IDX = 'ROOT' ");
        sb.append("    CONNECT BY PRIOR DEPT_ID = UP_DEPT_IDX ");
        sb.append("    ORDER BY DEPT_ORDER) A, ");
        sb.append("    (SELECT DEPT_BU, DEPT_ID, FUNCTION_TYPE ");
        sb.append("     FROM ADM_DEPT_MAPPING ");
        sb.append("     WHERE PLANT = :PLANT ) B ");
        sb.append("WHERE A.DEPT_ID = B.DEPT_ID(+) ");
        sb.append("  AND A.BU_ID = B.DEPT_BU(+) ");
        sb.append("  AND A.BU_ID IS NOT NULL ");

        if(selectDept != null && !selectDept.isEmpty()){
            sb.append("     AND INSTR(A.PATHE, :selectDept , 1) > 1                ");
        }
        sb.append("ORDER BY A.BU");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("selectDept", selectDept);

        List<AdmDeptDto> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(AdmDeptDto.class)
        );

        return result;
    }

    @Override
    public List<SysSystemCodeDataDto> getfunctionType(String plant) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DESCRIPTION, CODE_NAME ")
                .append("FROM SYS_SYSTEM_CODE_DATA ")
                .append("WHERE PLANT = :plant ")
                .append("AND TABLE_NAME = 'FUNCTION_TYPE' ")
                .append("AND NVL(SPECIAL_DATA1, 'Y') <> 'N' ")
                .append("ORDER BY CODE_SEQ");


        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant);


        List<SysSystemCodeDataDto> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(SysSystemCodeDataDto.class)
        );


        return result;
    }

    @Override
    public List<SysSystemCodeDataDto> getRegUserList(String plant) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT(GET_USERID_DESC_ONLY(plant, reg_user)) AS DESCRIPTION, reg_user AS VALUE ")
                .append("FROM QMS_KPI_LIST  ")
                .append("WHERE PLANT = :plant")
                .append("ORDER BY DESCRIPTION ");


        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant);


        List<SysSystemCodeDataDto> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(SysSystemCodeDataDto.class)
        );

        return result;

    }

    @Override
    public List<QmsKpiSearchDto> getSearch(QmsKpiSearchDto dto) {

        String plant =dto.getPlant();
        String systemName = dto.getSystemName();
        String docTitle = dto.getDocTitle();
        String docNo = dto.getDocNo();
        String deptBu = dto.getDeptBu();
        String dept = dto.getDept();
        String deptFunction =dto.getDeptFunction();
        String drafter = dto.getDrafter();
        String gubun = dto.getGubun();
        String process =dto.getProcess();
        List<String> regYear = dto.getRegYear();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT TO_CHAR(TO_DATE(LST.REG_DATE, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD') AS REG_DATE, ")
                .append("LST.QMS_NUMBER, ")
                .append("GET_DEPT_NAME_ONLY(LST.DEPT_BU) AS DEPT_BU, ")
                .append("GET_DEPT_NAME_ONLY(LST.DEPT_ID) AS DEPT_ID, ")
                .append("GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'QMS_CAUSE_TYPE', MAS.TYPE) AS TYPE, ")
                .append("NVL(GET_SYSCODE_DESC_ONLY(LST.PLANT, 'QMS_PROCESS_TYPE', LST.PROCESS), '-') AS PROCESS, ")
                .append("GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'KPI_CLASSIFICATION', MAS.CLASSIFICATION) AS CLASSIFICATION, ")
                .append("DOC.QMS_NUMBER AS DOC_NUMBER, ")
                .append("DOC.DOC_TITLE, ")
                .append("MAS.KPI, ")
                .append("GET_KPI_CYCLE(MAS.PLANT, MAS.KPI_NO) AS CYCLE, ")
                .append("MAS.CALC_FORMULA, ")
                .append("GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'QMS_KPI_UNIT', MAS.UNIT) AS UNIT, ")
                .append("GET_USERID_DESC_ONLY(LST.PLANT, LST.REG_USER) AS REG_USER, ")
                .append("GET_SYSCODE_DESC_ONLY(LST.PLANT, 'FUNCTION_TYPE', LST.FUNCTION_TYPE) AS FUNCTION_TYPE, ")
                .append("LST.DEPT_BU AS DEPT_BU_R, ")
                .append("LST.FUNCTION_TYPE AS FUNCTION_TYPE_R, ")
                .append("MAS.TYPE AS TYPE_R, ")
                .append("LST.REG_YEAR AS REG_YEAR_R ")
                .append("FROM QMS_KPI_LIST LST, QMS_KPI_MASTER MAS, QMS_DOCUMENT_STATUS DOC ")
                .append("WHERE 1 = 1 ")
                .append("AND LST.PLANT = :plant ")
                .append("AND LST.SYSTEM_NAME = :systemName ")
                .append("AND LST.PLANT = MAS.PLANT ")
                .append("AND LST.SYSTEM_NAME = MAS.SYSTEM_NAME ")
                .append("AND LST.KPI_NO = MAS.KPI_NO ")
                .append("AND MAS.PLANT = DOC.PLANT ")
                .append("AND DOC.SYSTEM_NAME = 'DOC' ")
                .append("AND MAS.DOC_NUMBER = DOC.QMS_NUMBER ")
                .append("AND DOC.REVISION_NO = (SELECT MAX(REVISION_NO) ")
                .append("FROM QMS_DOCUMENT_STATUS ")
                .append("WHERE PLANT = DOC.PLANT ")
                .append("AND SYSTEM_NAME = DOC.SYSTEM_NAME ")
                .append("AND QMS_NUMBER = DOC.QMS_NUMBER) ");

        if (docTitle != null && !docTitle.isEmpty())
            sb.append("AND DOC.QMS_NUMBER = :docTitle ");
        if (docNo != null && !docNo.isEmpty())
            sb.append("AND DOC.QMS_NUMBER = :docNo ");
        if (deptBu != null && !deptBu.isEmpty())
            sb.append("AND LST.DEPT_BU = :deptBu ");
        if (dept != null && !dept.isEmpty())
            sb.append("AND LST.DEPT_ID =  :dept ");
        if (deptFunction != null && !deptFunction.isEmpty())
            sb.append("AND LST.FUNCTION_TYPE = :deptFunction ");
        if (drafter != null && !drafter.isEmpty())
            sb.append("AND LST.REG_USER = :drafter ");
        if (gubun != null && !gubun.isEmpty())
            sb.append("AND MAS.TYPE = :gubun ");
        if (process != null && !process.isEmpty())
            sb.append("AND LST.PROCESS = :process ");
        if (regYear != null && !regYear.isEmpty())
            sb.append("AND MAS.REG_YEAR IN ( :regYear ) ");
        sb.append("ORDER BY LST.QMS_NUMBER ");


        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName",systemName)
                .addValue("docTitle", docTitle)
                .addValue("docNo",docNo)
                .addValue("deptBu", deptBu)
                .addValue("dept",dept)
                .addValue("deptFunction", deptFunction)
                .addValue("drafter",drafter)
                .addValue("gubun", gubun)
                .addValue("process",process)
                .addValue("regYear",regYear);

        List<QmsKpiSearchDto> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(QmsKpiSearchDto.class)
        );

        return result;
    }

    @Override
    public List<AdmDeptMappingVo> getDeptFunction(String plant, String deptId) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT GET_SYSCODE_DESC_ONLY(A.PLANT, 'FUNCTION_TYPE', A.FUNCTION_TYPE) AS FUNCTION_TYPE ")
                .append(", A.FUNCTION_TYPE AS FUNCTION_TYPE_CODE   ")
                .append(", A.DEPT_BU       ")
                .append(" FROM ADM_DEPT_MAPPING A   ")
                .append("WHERE A.PLANT = :plant  ")
                .append("AND A.DEPT_ID = :deptID");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("deptId",deptId);

        List<AdmDeptMappingVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(AdmDeptMappingVo.class)
        );

        return result;
    }

    @Override
    public List<KpiManagerVo> getKpiMaster(KpiManagerDto dto) {
        StringBuilder sb = new StringBuilder();
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String kpiType = dto.getKpiType();
        String regYear = dto.getRegYear();

        sb.append("SELECT MAS.KPI_NO\n");
        sb.append("       , MAS.DOC_NUMBER\n");
        sb.append("       , DOC.DOC_TITLE\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'KPI_CLASSIFICATION', MAS.CLASSIFICATION) AS CLASSIFICATION\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'QMS_KPI_UNIT', MAS.UNIT) AS UNIT\n");
        sb.append("       , MAS.CALC_FORMULA\n");
        sb.append("       , GET_KPI_CYCLE(MAS.PLANT, MAS.KPI_NO) AS CYCLE\n");
        // 동적인 부분은 주석 처리된 C# 코드에 따라 처리 필요
        sb.append("       , MAS.CLASSIFICATION AS CLASSIFICATION_R\n");
        sb.append("       , MAS.UNIT AS UNIT_R\n");
        sb.append("       , MAS.KPI\n");
        sb.append("  FROM QMS_KPI_MASTER MAS\n");
        sb.append("       , QMS_DOCUMENT_STATUS DOC\n");
        sb.append(" WHERE 1 = 1\n");
        sb.append("   AND MAS.PLANT = :plant \n");
        sb.append("   AND MAS.SYSTEM_NAME = :systemName \n");
        sb.append("   AND MAS.TYPE = :kpiType \n");
        sb.append("   AND MAS.REG_YEAR = :regYear \n");
        sb.append("   AND MAS.PLANT = DOC.PLANT\n");
        sb.append("   AND DOC.SYSTEM_NAME = 'DOC'\n");
        sb.append("   AND MAS.USE_FLAG = 'Y'\n");
        sb.append("   AND MAS.DOC_NUMBER = DOC.QMS_NUMBER\n");
        sb.append("   AND DOC.REVISION_NO = (SELECT MAX(REVISION_NO)\n");
        sb.append("                            FROM QMS_DOCUMENT_STATUS\n");
        sb.append("                           WHERE PLANT = DOC.PLANT\n");
        sb.append("                             AND SYSTEM_NAME = DOC.SYSTEM_NAME\n");
        sb.append("                             AND QMS_NUMBER = DOC.QMS_NUMBER)\n");
        sb.append(" ORDER BY MAS.KPI_NO\n");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName",systemName)
                .addValue("regYear",regYear)
                .addValue("kpiType",kpiType);

        List<KpiManagerVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(KpiManagerVo.class)
        );

        return result;
    }

    @Override
    public List<KpiManagerVo> getRegKpiData(KpiManagerDto dto) {

        StringBuilder sb = new StringBuilder();
        String plant = dto.getPlant();
        String systemName =dto.getSystemName();
        String kpiType = dto.getKpiType();
        String deptBu = dto.getDeptBu();
        String functionType = dto.getFunctionType();
        String regYear = dto.getRegYear();
        List<SysSystemCodeDataVo> cycleList = dto.getCycleList();
        sb.append("SELECT '' AS REMOVE\n");
        sb.append("       , LST.KPI_NO\n");
        sb.append("       , LST.PROCESS\n");
        sb.append("       , LST.WEIGHT\n");
        sb.append("       , MAS.DOC_NUMBER\n");
        sb.append("       , DOC.DOC_TITLE\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'KPI_CLASSIFICATION', MAS.CLASSIFICATION) AS CLASSIFICATION\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(MAS.PLANT, 'QMS_KPI_UNIT', MAS.UNIT) AS UNIT\n");
        sb.append("       , MAS.CALC_FORMULA\n");
        sb.append("       , GET_KPI_CYCLE(MAS.PLANT, MAS.KPI_NO) AS CYCLE\n");
        sb.append("       , GET_DEPT_NAME_ONLY(LST.DEPT_BU) AS BU_NAME\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(LST.PLANT, 'FUNCTION_TYPE', LST.FUNCTION_TYPE) AS FUNCTION_TYPE\n");
        sb.append("       , MAS.CLASSIFICATION AS CLASSIFICATION_R\n");
        sb.append("       , MAS.UNIT AS UNIT_R\n");
        sb.append("       , MAS.KPI\n");
        sb.append("       , LST.DEPT_BU\n");
        sb.append("       , LST.DEPT_ID\n");
        sb.append("       , LST.FUNCTION_TYPE AS FUNCTION_TYPE_R\n");
        sb.append("       , LST.QMS_NUMBER\n");
        sb.append("       , LST.REG_USER\n");
        sb.append("       , LST.REG_DATE\n");
        sb.append("       , 'N' AS ISNEW\n");
        sb.append("  FROM QMS_KPI_LIST LST\n");
        sb.append("       , QMS_KPI_MASTER MAS\n");
        sb.append("       , QMS_DOCUMENT_STATUS DOC\n");
        sb.append(" WHERE 1 = 1\n");
        sb.append("   AND LST.PLANT = :plant \n");
        sb.append("   AND LST.SYSTEM_NAME = :systemName \n");
        sb.append("   AND LST.REG_YEAR = :regYear \n");
        sb.append("   AND LST.PLANT = MAS.PLANT\n");
        sb.append("   AND LST.SYSTEM_NAME = MAS.SYSTEM_NAME\n");
        sb.append("   AND LST.KPI_NO = MAS.KPI_NO\n");
        sb.append("   AND LST.REG_YEAR = MAS.REG_YEAR\n");
        sb.append("   AND MAS.TYPE = :kpiType \n");

        if ("INT".equals(kpiType)) {
            sb.append("   AND LST.DEPT_BU =:deptBu \n");
            sb.append("   AND LST.FUNCTION_TYPE = :functionType \n");
        }

        sb.append("   AND MAS.PLANT = DOC.PLANT\n");
        sb.append("   AND DOC.SYSTEM_NAME = 'DOC'\n");
        sb.append("   AND MAS.DOC_NUMBER = DOC.QMS_NUMBER\n");
        sb.append("   AND DOC.REVISION_NO = (SELECT MAX(REVISION_NO)\n");
        sb.append("                            FROM QMS_DOCUMENT_STATUS\n");
        sb.append("                           WHERE PLANT = DOC.PLANT\n");
        sb.append("                             AND SYSTEM_NAME = DOC.SYSTEM_NAME\n");
        sb.append("                             AND QMS_NUMBER = DOC.QMS_NUMBER)\n");
        sb.append(" ORDER BY LST.QMS_NUMBER\n");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName",systemName)
                .addValue("regYear",regYear)
                .addValue("kpiType",kpiType)
                .addValue("deptBu",deptBu)
                .addValue("functionType",functionType);


        List<KpiManagerVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(KpiManagerVo.class)
        );

        return result;
    }

    @Override
    public List<KpiManagerVo> getKpiDel(KpiManagerDto dto) {

        String plant = dto.getPlant();
        String systemName =dto.getSystemName();
        String revision = dto.getRevision();
        String qmsNumber = dto.getQmsNumber();
        List<SysSystemCodeDataVo> dtCycle = dto.getCycleList();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DECODE(COUNT('X'), 0, 'Y', 'N') AS IS_DEL\n");
        sb.append("  FROM QMS_KPI_RESULT_DETAIL_STATUS DET\n");
        sb.append(" WHERE 1 = 1\n");
        sb.append("   AND DET.PLANT = :plant  \n");
        sb.append("   AND DET.SYSTEM_NAME = :systemName \n");
        sb.append("   AND DET.REVISION_NO = :revision \n");
        sb.append("   AND DET.LST_QMS_NUMBER = :qmsNumber \n");
        sb.append("   AND DET.LST_REVISION_NO = :revision \n");
        sb.append("   AND (\n");
        sb.append("        TARGET IS NOT NULL\n");
        for (SysSystemCodeDataVo sysSystemCodeDataVo : dtCycle) {
            String codeName = sysSystemCodeDataVo.getCodeName();
            sb.append("        OR RESULT_").append(codeName).append(" IS NOT NULL\n");
        }
        sb.append("       )\n");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName",systemName)
                .addValue("revision",revision)
                .addValue("qmsNumber",qmsNumber);


        List<KpiManagerVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(KpiManagerVo.class)
        );

        return result;
    }

    @Override
    public List<MailVo> searchInUserList(KpiManagerDto dto) {
        String plant = dto.getPlant();
        String systemName =dto.getSystemName();
        String deptBu = dto.getDeptBu();
        String functionType = dto.getFunctionType();
        String mode = dto.getMode();
        String userId = dto.getUserId();
        boolean isReg = dto.isReg();

        StringBuilder sb = new StringBuilder();


        if (isReg) {
            if ("INT".equals(mode)) {
                sb.append("SELECT LISTAGG(DEPT.DEPT_NAME || '<' || DEPT.DEPT_ID || '@siliconmitus.com>', ',') WITHIN GROUP (ORDER BY DEPT.DEPT_NAME) AS EMAIL\n");
                sb.append("  FROM ADM_DEPT DEPT\n");
                sb.append("       , ADM_DEPT_MAPPING MAP\n");
                sb.append(" WHERE 1 = 1\n");
                sb.append("   AND DEPT.DEPT_STATUS <> 'D'\n");
                sb.append("   AND DEPT.DEPT_ID = MAP.DEPT_ID\n");
                sb.append("   AND MAP.PLANT = :plant \n");
                sb.append("   AND MAP.DEPT_BU = :deptBu \n");
                sb.append("   AND MAP.FUNCTION_TYPE = :functionType \n");
            } else {
                sb.append("SELECT LISTAGG(DEPT.DEPT_NAME || '<' || DEPT.DEPT_ID || '@siliconmitus.com>', ',') WITHIN GROUP (ORDER BY SYS.CODE_SEQ) AS EMAIL\n");
                sb.append("  FROM SYS_SYSTEM_CODE_DATA SYS\n");
                sb.append("       , ADM_DEPT DEPT\n");
                sb.append(" WHERE SYS.PLANT = :plant \n");
                sb.append("   AND SYS.TABLE_NAME = 'QMS_KPI_SUP_MAIL'\n");
                sb.append("   AND SYS.CODE_NAME = DEPT.DEPT_ID\n");
                sb.append("   AND DEPT.DEPT_STATUS <> 'D'\n");
            }
        } else {
            sb.append("SELECT LISTAGG(EMP.USER_NAME || '<' || EMP.EMAIL_ID || '>', ',') WITHIN GROUP (ORDER BY EMP.USER_NAME) AS EMAIL\n");
            sb.append("  FROM ADM_USER_INFO EMP\n");
            sb.append(" WHERE EMP.PLANT = :plant \n");
            sb.append("   AND EMP.EXPAND_FLAG3 <> 'R'\n");
            sb.append("   AND EMP.USER_ID IN (SELECT DISTINCT UPDATE_USER AS EMAIL_USER\n");
            sb.append("                          FROM QMS_KPI_LIST LST\n");
            sb.append("                         WHERE 1 = 1\n");
            sb.append("                           AND LST.PLANT = :plant \n");
            sb.append("                           AND LST.SYSTEM_NAME = :systemName \n");
            sb.append("                           AND LST.TYPE = :mode \n");
            if ("INT".equals(mode)) {
                sb.append("                           AND LST.DEPT_BU = :deptBu \n");
                sb.append("                           AND LST.FUNCTION_TYPE = :functionType \n");
            }
            sb.append("                        UNION\n");
            sb.append("                        SELECT :userId AS EMAIL_USER\n");
            sb.append("                          FROM DUAL)\n");
        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName",systemName)
                .addValue("deptBu",deptBu)
                .addValue("functionType",functionType)
                .addValue("userId",userId)
                .addValue("mode",mode);


        List<MailVo> result = jdbcTemplate.query(
                sb.toString(),
                namedParameters,
                new CustomBeanPropertyRowMapper<>(MailVo.class)
        );

        return result;
    }

    @Override
    public void kpiDeleteAction(String plant, String systemName, String revision, String deleteKpi,String regYear, String mode, String deptBu, String functionType) {


        if (deleteKpi != null && !deleteKpi.isEmpty()) {
            deleteFromResultDetailStatus(plant, systemName, revision, deleteKpi);
            deleteFromResultDetailHistory(plant, systemName, revision, deleteKpi);
            deleteFromResultStatus(plant, systemName);
            deleteFromResultHistory(plant, systemName);
        }
        deleteFromKpiList(plant,systemName,revision,regYear,mode,deptBu,functionType);



    }

    @Override
    public String getQmsYear(String plant, String systemName, String regYear) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT GET_QMSNUMBER_YEAR( :plant ,  :systemName, '', '',  :regYear ) FROM DUAL ");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("regYear", regYear);

        return jdbcTemplate.queryForObject(sb.toString(), namedParameters, String.class);
    }

    private void deleteFromResultDetailStatus(String plant, String systemName, String revision, String deleteKpi) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM QMS_KPI_RESULT_DETAIL_STATUS ");
        sb.append( "WHERE PLANT = :plant AND SYSTEM_NAME = :systemName " );
        sb.append( "AND LST_QMS_NUMBER || LST_REVISION_NO IN (SELECT QMS_NUMBER || REVISION_NO " );
        sb.append( "FROM QMS_KPI_LIST WHERE PLANT = :plant AND SYSTEM_NAME = :systemName " );
        sb.append( "AND REVISION_NO = :revision AND QMS_NUMBER IN ( :deleteKpi ))");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("revision", revision)
                .addValue("deleteKpi", deleteKpi);

        jdbcTemplate.update(sb.toString(), namedParameters);
    }

    private void deleteFromResultDetailHistory(String plant, String systemName, String revision, String deleteKpi) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM QMS_KPI_RESULT_DETAIL_HISTORY ");
        sb.append( "WHERE PLANT = :plant AND SYSTEM_NAME = :systemName " );
        sb.append( "AND LST_QMS_NUMBER || LST_REVISION_NO IN (SELECT QMS_NUMBER || REVISION_NO  " );
        sb.append( " FROM QMS_KPI_LIST WHERE PLANT = :plant AND SYSTEM_NAME = :systemName " );
        sb.append( "AND REVISION_NO = :revision AND QMS_NUMBER IN ( :deleteKpi ))");

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("revision", revision)
                .addValue("deleteKpi", deleteKpi);

        jdbcTemplate.update(sb.toString(), namedParameters);
    }
    private void deleteFromResultStatus(String plant, String systemName) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE\n");
        sb.append("  FROM QMS_KPI_RESULT_STATUS STS\n");
        sb.append(" WHERE PLANT = :plant \n");
        sb.append("   AND SYSTEM_NAME = :systemName \n");
        sb.append("   AND NOT EXISTS (SELECT 'X'\n");
        sb.append("                     FROM QMS_KPI_RESULT_DETAIL_STATUS DET\n");
        sb.append("                    WHERE DET.PLANT = STS.PLANT\n");
        sb.append("                      AND DET.SYSTEM_NAME = STS.SYSTEM_NAME\n");
        sb.append("                      AND DET.QMS_NUMBER = STS.QMS_NUMBER\n");
        sb.append("                      AND DET.REVISION_NO = STS.REVISION_NO)\n");


        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName);


        jdbcTemplate.update(sb.toString(), namedParameters);
    }
    private void deleteFromResultHistory(String plant, String systemName) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE\n");
        sb.append("  FROM QMS_KPI_RESULT_HISTORY HST\n");
        sb.append(" WHERE PLANT = :plant \n");
        sb.append("   AND SYSTEM_NAME = :systemName \n");
        sb.append("   AND NOT EXISTS (SELECT 'X'\n");
        sb.append("                     FROM QMS_KPI_RESULT_DETAIL_STATUS DET\n");
        sb.append("                    WHERE DET.PLANT = HST.PLANT\n");
        sb.append("                      AND DET.SYSTEM_NAME = HST.SYSTEM_NAME\n");
        sb.append("                      AND DET.QMS_NUMBER = HST.QMS_NUMBER\n");
        sb.append("                      AND DET.REVISION_NO = HST.REVISION_NO)\n");



        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName);


        jdbcTemplate.update(sb.toString(), namedParameters);
    }
    private void deleteFromKpiList(String plant, String systemName, String revision, String regYear, String mode, String deptBu, String functionType) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM QMS_KPI_LIST WHERE 1 = 1 ");
        sb.append("AND PLANT = :plant AND SYSTEM_NAME = :systemName AND REVISION_NO = :revision AND REG_YEAR = :regYear ");
        sb.append("AND EXISTS (SELECT 'X' FROM QMS_KPI_MASTER MAS WHERE MAS.PLANT = LST.PLANT AND MAS.SYSTEM_NAME = LST.SYSTEM_NAME AND MAS.KPI_NO = LST.KPI_NO AND MAS.TYPE = :mode) ");

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("plant", plant)
                .addValue("systemName", systemName)
                .addValue("revision", revision)
                .addValue("regYear", regYear)
                .addValue("mode", mode);

        if ("INT".equals(mode)) {
            sb.append("AND DEPT_BU = :deptBu AND FUNCTION_TYPE = :functionType ");
            parameters.addValue("deptBu", deptBu)
                    .addValue("functionType", functionType);
        }

        jdbcTemplate.update(sb.toString(), parameters);
    }

}
