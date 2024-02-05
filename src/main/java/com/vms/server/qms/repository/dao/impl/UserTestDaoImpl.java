package com.vms.server.qms.repository.dao.impl;

import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.qms.repository.dao.UserTestDao;
import com.vms.server.domain.dto.AdmUserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserTestDaoImpl implements UserTestDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<AdmUserInfoDto> getUserInfoListById(String userId) {

        StringBuilder sql = new StringBuilder("SELECT * FROM ADM_USER_INFO WHERE 1=1");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (userId != null) {
            sql.append(" AND USER_ID = :userId");
            parameters.addValue("userId", userId);
        }

        List<AdmUserInfoDto> results = jdbcTemplate.query(
                sql.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(AdmUserInfoDto.class)
        );

        return results;
    }
}
