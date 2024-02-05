package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminSysMenuActionDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.SysMenuActionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminSysMenuActionDaoImpl implements AdminSysMenuActionDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<SysMenuActionDto> getSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        String plant = sysMenuActionDto.getPlant();
        String moduleId = sysMenuActionDto.getModuleId();
        String actionName = sysMenuActionDto.getActionNameKor();
        String action = sysMenuActionDto.getAction();
        String orderColumn = "";

        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ROWNUM,");
//        sb.append("       GET_SYSCODE_DESC(:plant, 'APP_TYPE', MODULE_ID) AS module_id,");
//        sb.append("       ACTION_NAME_KOR,");
//        sb.append("       ACTION_NAME_ENG AS ACTION_NAME_ENG,");
//        sb.append("       ACTION_NAME_01 AS ACTION_NAME_01,");
//        sb.append("       ACTION_NAME_02 AS ACTION_NAME_02,");
//        sb.append("       ACTION_NAME_03 AS ACTION_NAME_03,");
//        sb.append("       ACTION,");
//        sb.append("       ACTION_SEQ,");
//        sb.append("       ACTION_TYPE,");
//        sb.append("       ENABLE_TOOLBAR, ");
//        sb.append("       TOOLBAR_ICON, ");
//        sb.append("       TOOLBAR_TEXT_KOR, ");
//        sb.append("       TOOLBAR_TEXT_ENG, ");
//        sb.append("       TOOLBAR_TEXT_01, ");
//        sb.append("       ( SELECT COUNT(MENU_ID)");
//        sb.append("           FROM SYS_MENU_STRUCTURE");
//        sb.append("          WHERE ACTION_SEQ = A.ACTION_SEQ");
//        sb.append("            AND PLANT = :plant");
//        sb.append("       ) AS MENU_COUNT,");
//        sb.append("       CONTROL_TYPE,");
//        sb.append("       CONTROL_VALUE,");
//        sb.append("       EXPAND_FIELD1,");
//        sb.append("       EXPAND_FIELD2,");
//        sb.append("       EXPAND_FIELD3,");
//        sb.append("       EXPAND_FIELD4,");
//        sb.append("       EXPAND_FIELD5 ");
//        sb.append("  FROM SYS_MENU_ACTION A");
        sb.append("SELECT ROWNUM,");
        sb.append("       GET_SYSCODE_DESC(:plant, 'APP_TYPE', MODULE_ID) AS MODULE_ID,");
        sb.append("       ACTION_NAME_KOR,");
        sb.append("       ACTION_NAME_ENG AS ACTION_NAME_ENG,");
        sb.append("       ACTION_NAME_01 AS ACTION_NAME01,");
        sb.append("       ACTION_NAME_02 AS ACTION_NAME02,");
        sb.append("       ACTION_NAME_03 AS ACTION_NAME03,");
        sb.append("       ACTION,");
        sb.append("       ACTION_SEQ,");
        sb.append("       ACTION_TYPE,");
        sb.append("       DECODE(ACTION_TYPE,'F','폼', '이벤트') AS ACTION_TYPE_NAME,");
        sb.append("       ENABLE_TOOLBAR,                                           ");
        sb.append("       DECODE(ENABLE_TOOLBAR,'Y','YES','NO') AS ENABLE_TOOLBAR_NAME,	");
        sb.append("       ( SELECT COUNT(MENU_ID)");
        sb.append("           FROM SYS_MENU_STRUCTURE");
        sb.append("          WHERE ACTION_SEQ = A.ACTION_SEQ");
        sb.append("            AND PLANT = :plant");
        sb.append("       ) AS MENU_COUNT,");
        sb.append("       CONTROL_TYPE,");
        sb.append("       CONTROL_VALUE");
        sb.append("  FROM SYS_MENU_ACTION A");
        sb.append("  WHERE 1 = 1");

        if (moduleId != null && !moduleId.isEmpty()) {
            sb.append(" AND MODULE_ID = :moduleId");
        }
        if (actionName != null && !actionName.isEmpty()){
            sb.append(" AND UPPER(ACTION_NAME_KOR) LIKE UPPER('%").append(actionName).append("%') ");
            orderColumn = "action_name_kor";
        }
        if (action != null && !action.isEmpty()){
            sb.append(" AND UPPER(ACTION) LIKE UPPER('%").append(action).append("%') ");
            orderColumn = "action";
        }
        sb.append(" ORDER BY MODULE_ID, :orderColumn ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("moduleId", moduleId);
        parameters.addValue("orderColumn", orderColumn);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysMenuActionDto.class)
        );
    }

    @Override
    public int getActionSeq(SysMenuActionDto sysMenuActionDto) {
        String moduleId = sysMenuActionDto.getModuleId();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT NVL(MAX(ACTION_SEQ),:num1-1) +1 FROM SYS_MENU_ACTION WHERE ACTION_SEQ BETWEEN :num1 AND :num2");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        switch (moduleId) {
            case "COMMON":
                parameters.addValue("num1", "1000");
                parameters.addValue("num2", "1999");
                break;
            case "ADMIN":
                parameters.addValue("num1", "2000");
                parameters.addValue("num2", "2999");
                break;
            case "QMS":
                parameters.addValue("num1", "3000");
                parameters.addValue("num2", "3999");
                break;
            case "ASFC":
                parameters.addValue("num1", "4000");
                parameters.addValue("num2", "4999");
                break;
            case "REPORT":
                parameters.addValue("num1", "5000");
                parameters.addValue("num2", "5999");
                break;
            case "WIPMONITORING":
                parameters.addValue("num1", "6000");
                parameters.addValue("num2", "6999");
                break;
            default:
                parameters.addValue("num1", "7000");
                parameters.addValue("num2", "9999");
                break;
        }

        return jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                Integer.class
        );
    }
}
