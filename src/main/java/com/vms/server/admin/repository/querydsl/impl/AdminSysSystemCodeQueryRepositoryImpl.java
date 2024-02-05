package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminSysSystemCodeQueryRepository;
import com.vms.server.domain.dto.SysSystemCodeDto;
import com.vms.server.domain.entity.sys.SysSystemCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vms.server.domain.entity.sys.QSysSystemCode.sysSystemCode;

@Repository
@RequiredArgsConstructor
public class AdminSysSystemCodeQueryRepositoryImpl implements AdminSysSystemCodeQueryRepository {

    final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SysSystemCode> getSysSystemCode(SysSystemCodeDto sysSystemCodeDto) {
        String plant = sysSystemCodeDto.getPlant();
        String tableName = sysSystemCodeDto.getTableName();
        String description = sysSystemCodeDto.getDescription();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(sysSystemCode.plant.eq(plant));
        if (tableName != null && !tableName.isEmpty()){
            builder.and(sysSystemCode.tableName.containsIgnoreCase(tableName));
        }
        if (description != null && !description.isEmpty()){
            builder.and(sysSystemCode.description.containsIgnoreCase(description));
        }

        return jpaQueryFactory
                .selectFrom(sysSystemCode)
                .where(builder)
                .orderBy(sysSystemCode.tableName.asc())
                .fetch();
    }
}
