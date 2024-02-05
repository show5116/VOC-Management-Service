package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.AdminAdmPlantDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.vo.AdmPlantVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class AdminAdmPlantDaoImpl implements AdminAdmPlantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<AdmPlantVo> searchPlant(String likeColumn, String likeKeyword, String activePlant) {


        StringBuilder sb = new StringBuilder();
        sb.append("select description,")
                .append("       plant, ")
                .append("       shift_1_start, ")
                .append("       case when shift_1_start is null then '' ")
                .append("            else substr(shift_1_start,0,2)|| ':' || substr(shift_1_start,3,2) end ")
                .append("       as formatted_shift1, ")
                .append("       shift_2_start, ")
                .append("       case when shift_2_start is null then '' ")
                .append("            else substr(shift_2_start,0,2)|| ':' || substr(shift_2_start,3,2) end ")
                .append("       as formatted_shift2, ")
                .append("       shift_3_start, ")
                .append("       case when shift_3_start is null then '' ")
                .append("            else substr(shift_3_start,0,2)|| ':' || substr(shift_3_start,3,2) end ")
                .append("       as formatted_shift3, ")
                .append("       shift_4_start, ")
                .append("       case when shift_4_start is null then '' ")
                .append("            else substr(shift_4_start,0,2)|| ':' || substr(shift_4_start,3,2) end ")
                .append("       as formatted_shift4, ")
                .append("       std_days_per_week, ")
                .append("       std_hours_per_day, ")
                .append("       active_plant, ")
                .append("       case when active_plant = 'Y' then 'Yes' else 'No' end as active_plant_name   ")
                .append("  from adm_plant ");

        if(activePlant != null && activePlant.equals("Y")) {
            sb.append("  where active_plant in ( 'Y' ) ");
        }else{
            sb.append("  where active_plant in ( 'Y','N' ) ");
        }

        if (likeColumn != null && !likeColumn.isEmpty()) {
                sb.append(String.format("   and %s like '%'|| :likeKeyword ||'%'   \r\n", likeColumn));
            }
        MapSqlParameterSource parameters = new MapSqlParameterSource();


        parameters.addValue("likeKeyword", likeKeyword);

        List<AdmPlantVo> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmPlantVo.class)
        );

        return result;
    }
}
