package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminSysMenuGroupDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.dto.SysMenuDto;
import com.vms.server.domain.dto.SysMenuGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminSysMenuGroupDaoImpl implements AdminSysMenuGroupDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<SysMenuGroupDto> getSysMenuGroup(SysMenuGroupDto sysMenuGroupDto) {
        String plant = sysMenuGroupDto.getPlant();
        String moduleId = sysMenuGroupDto.getModuleId();
        String groupId = sysMenuGroupDto.getGroupId();
        String groupName = sysMenuGroupDto.getGroupName();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ROWNUM, ");
        sb.append("       PLANT, ");
        sb.append("       GROUP_ID, ");
        sb.append("       GROUP_NAME, ");
        sb.append("       MODULE_ID, ");
        sb.append("       GET_SYSCODE_DESC(PLANT, 'APP_TYPE', MODULE_ID) AS MODULE_DESC,");
        sb.append("       DESCRIPTION");
        sb.append("  FROM SYS_MENU_GROUP M ");
        sb.append(" WHERE VISIBLE_FLAG = 'Y' ");
        if (plant != null && !plant.isEmpty()) {
            sb.append(" AND PLANT = :plant");
        }
        if (moduleId != null && !moduleId.isEmpty()) {
            sb.append(" AND MODULE_ID = :moduleId");
        }
        boolean flag = true;
        if (groupId != null && !groupId.isEmpty()){
            sb.append(" AND UPPER(GROUP_ID) LIKE UPPER('%").append(groupId).append("%') ");
            sb.append(" ORDER BY GROUP_ID");
            flag = false;
        }
        if (groupName != null && !groupName.isEmpty()){
            sb.append(" AND UPPER(GROUP_NAME) LIKE UPPER('%").append(groupName).append("%') ");
            sb.append(" ORDER BY GROUP_NAME");
            flag = false;
        }
        if (flag) {
            sb.append(" ORDER BY PLANT, GROUP_ID, MODULE_ID");
        }

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("moduleId", moduleId);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysMenuGroupDto.class)
        );
    }

    @Override
    public List<SysMenuDto> getMenuTree(SysMenuGroupDto sysMenuGroupDto) {
        String plant = sysMenuGroupDto.getPlant();
        String moduleId = sysMenuGroupDto.getModuleId();
        String groupId = sysMenuGroupDto.getGroupId();
        String menuName = "menu_name_kor";
        String actionName = "action_name_kor";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT B.PLANT AS PLANT,      ");
        sb.append("       B.GROUP_ID AS GROUP_ID,   ");
        sb.append("       B.MENU_ID AS MENU_ID,    ");
        sb.append("       B.MODULE_ID AS MODULE_ID,  ");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL)  ");
        sb.append("            THEN 'Y' ELSE 'N'                ");
        sb.append("       END AS IS_ACTION_MENU,                ");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.").append(menuName).append(" IS NULL)  ");
        sb.append("            THEN C.").append(actionName).append(" ELSE B.").append(menuName);
        sb.append("       END AS MENU_NAME,");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.MENU_NAME_KOR IS NULL)  ");
        sb.append("            THEN C.ACTION_NAME_KOR ELSE B.MENU_NAME_KOR ");
        sb.append("       END AS MENU_NAME_KOR,");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.MENU_NAME_ENG IS NULL)  ");
        sb.append("            THEN C.ACTION_NAME_ENG ELSE B.MENU_NAME_ENG ");
        sb.append("       END AS MENU_NAME_ENG,");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.MENU_NAME_01 IS NULL)  ");
        sb.append("            THEN C.ACTION_NAME_01 ELSE B.MENU_NAME_01 ");
        sb.append("       END AS MENU_NAME_01,");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.MENU_NAME_02 IS NULL)  ");
        sb.append("            THEN C.ACTION_NAME_02 ELSE B.MENU_NAME_02 ");
        sb.append("       END AS MENU_NAME_02,");
        sb.append("       CASE WHEN (C.ACTION_SEQ IS NOT NULL AND B.MENU_NAME_03 IS NULL)  ");
        sb.append("            THEN C.ACTION_NAME_03 ELSE B.MENU_NAME_03 ");
        sb.append("       END AS MENU_NAME_03,");
        sb.append("       B.HAS_CHILD AS HAS_CHILD,      ");
        sb.append("       B.PARENT_KEY AS PARENT_KEY,     ");
        sb.append("       B.DISPLAY_DEPTH AS DISPLAY_DEPTH,  ");
        sb.append("       C.TOOLBAR_ICON AS TOOLBAR_ICON,       ");
        sb.append("       B.PATH AS PATH,           ");
        sb.append("       B.SEPERATE_BAR AS SEPERATE_BAR,    ");
        sb.append("       C.ACTION_SEQ AS ACTION_SEQ,     ");
        sb.append("       C.ACTION_TYPE AS ACTION_TYPE,    ");
        sb.append("       C.ACTION AS ACTION,         ");
        sb.append("       B.SHORTCUT_KEY AS SHORTCUT_KEY,                          ");
        sb.append("       C.CONTROL_TYPE AS CONTROL_TYPE,   ");
        sb.append("       C.CONTROL_VALUE AS CONTROL_VALUE,  ");
        sb.append("       B.VISIBLE_FLAG AS VISIBLE_FLAG,                          ");
        sb.append("       B.ENABLE_FLAG AS ENABLED_FLAG,                           ");
        sb.append("       B.EXPAND_FLAG1 AS ADMIN_VIEWMODE,        ");
        sb.append("       B.DESCRIPTION AS DESCRIPTION,    ");
        sb.append("       B.EXPAND_FIELD1 AS EXPAND_FIELD1,  ");
        sb.append("       B.EXPAND_FIELD2 AS EXPAND_FIELD2,  ");
        sb.append("       B.EXPAND_FIELD3 AS EXPAND_FIELD3,  ");
        sb.append("       B.EXPAND_FIELD4 AS EXPAND_FIELD4,  ");
        sb.append("       B.EXPAND_FIELD5 AS EXPAND_FIELD5,   ");
        sb.append("       C.EXPAND_FIELD1 AS ACT_EXPAND_FIELD1,                                   ");
        sb.append("       C.EXPAND_FIELD2 AS ACT_EXPAND_FIELD2,                                   ");
        sb.append("       C.EXPAND_FIELD3 AS ACT_EXPAND_FIELD3,                                   ");
        sb.append("       C.EXPAND_FIELD4 AS ACT_EXPAND_FIELD4,                                   ");
        sb.append("       C.EXPAND_FIELD5 AS ACT_EXPAND_FIELD5                                    ");
        sb.append("  FROM SYS_MENU_GROUP A,     ");
        sb.append("       SYS_MENU_STRUCTURE B,           ");
        sb.append("       SYS_MENU_ACTION C     ");
        sb.append(" WHERE A.PLANT = :plant");
        sb.append("   AND A.PLANT = B.PLANT ");
        sb.append("   AND A.MODULE_ID = B.MODULE_ID ");
        sb.append("   AND A.MODULE_ID = :moduleId");
        sb.append("   AND A.GROUP_ID = B.GROUP_ID   ");
        sb.append("   AND A.GROUP_ID = :groupId");
        sb.append("   AND B.ACTION_SEQ  = C.ACTION_SEQ (+)  ");
        sb.append(" ORDER BY MENU_ID        ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);
        parameters.addValue("moduleId", moduleId);
        parameters.addValue("groupId", groupId);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysMenuDto.class)
        );
    }

    @Override
    public List<SysMenuGroupDto> getConditionModule(SysMenuGroupDto sysMenuGroupDto) {
        String plant = sysMenuGroupDto.getPlant();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT GET_SYSCODE_DESC(:plant, 'APP_TYPE', MODULE_ID) AS MODULEDESC, MODULE_ID, TO_SYSCODESEQ(:plant, 'APP_TYPE', MODULE_ID) AS ORDERSEQ " );
        sb.append("FROM (SELECT MODULE_ID FROM SYS_MENU_GROUP WHERE PLANT = :plant UNION ALL SELECT MODULE_ID FROM SYS_MENU_ACTION) ");
        sb.append("ORDER BY ORDERSEQ");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("plant", plant);

        return jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(SysMenuGroupDto.class)
        );
    }
}
