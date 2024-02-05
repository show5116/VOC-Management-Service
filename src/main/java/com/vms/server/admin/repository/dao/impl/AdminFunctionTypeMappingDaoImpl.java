package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminFunctionTypeMappingDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminFunctionTypeMappingDaoImpl implements AdminFunctionTypeMappingDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<AdmDeptMappingVo> getFunctionTypeMappingData(String plant, String selectDept) {

        StringBuilder sb = new StringBuilder();


        sb.append(" SELECT A.DEPT_ID\n");
        sb.append("       ,A.BU_ID\n");
        sb.append("       ,A.BU\n");
        sb.append("       ,A.DEPT_NAME\n");
        sb.append("       ,B.FUNCTION_TYPE\n");
        sb.append("  FROM (\n");
        sb.append("         SELECT DEPT_ID\n");
        sb.append("               , SUBSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_ID, '|') || '|', '|', 2, 2) - 1) as BU_ID\n");
        sb.append("               ,SUBSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) + 1, INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 1, 4) - INSTR(SYS_CONNECT_BY_PATH(DEPT_NAME, '|') || '|', '|', 2, 2) - 1) as BU\n");
        sb.append("               ,DEPT_NAME\n");
        sb.append("               ,'' AS FUNCTION_TYPE\n");
        sb.append("               , SYS_CONNECT_BY_PATH(DEPT_ID, '|') AS PATH\n");
        sb.append("            FROM (SELECT * FROM ADM_DEPT WHERE DEPT_STATUS <> 'D') A\n");
        sb.append("           WHERE 1 = 1\n");
        sb.append("             AND CONNECT_BY_ISLEAF = '1'\n");
        sb.append("           START WITH UP_DEPT_IDX = 'ROOT'\n");
        sb.append("         CONNECT BY PRIOR DEPT_ID = UP_DEPT_IDX\n");
        sb.append("         ORDER BY DEPT_ORDER ) A\n");
        sb.append("         ,(\n");
        sb.append("             SELECT DEPT_BU\n");
        sb.append("                    , DEPT_ID\n");
        sb.append("                    , FUNCTION_TYPE\n");
        sb.append("               FROM ADM_DEPT_MAPPING\n");
        sb.append("              WHERE PLANT = :plant\n");
        sb.append("         ) B\n");
        sb.append(" WHERE A.DEPT_ID = B.DEPT_ID(+)\n");
        sb.append("   AND A.BU_ID = B.DEPT_BU(+)\n");
        sb.append("   AND A.BU_ID IS NOT NULL\n");

        if (selectDept != null && !selectDept.isEmpty()) {
            sb.append("   AND INSTR(A.PATH, :selectDept , 1) > 1\n");
        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("selectDept", selectDept);


        List<AdmDeptMappingVo> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmDeptMappingVo.class)
        );
        return result;
    }
}
