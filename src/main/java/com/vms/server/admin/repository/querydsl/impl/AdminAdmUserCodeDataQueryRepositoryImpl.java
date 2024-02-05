package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmUserCodeDataQueryRepository;
import com.vms.server.domain.dto.AdmUserCodeAndDataDto;
import com.vms.server.domain.entity.adm.AdmUserCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vms.server.domain.entity.adm.QAdmUserCode.admUserCode;
import static com.vms.server.domain.entity.adm.QAdmUserCodeData.admUserCodeData;

@Repository
@RequiredArgsConstructor
public class AdminAdmUserCodeDataQueryRepositoryImpl implements AdminAdmUserCodeDataQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmUserCode> getAdmUserCode(AdmUserCodeAndDataDto admUserCodeAndDataDto) {
        String plant = admUserCodeAndDataDto.getPlant();
        String tableName = admUserCodeAndDataDto.getTableName();
        String description = admUserCodeAndDataDto.getDescription();
        String tableType = admUserCodeAndDataDto.getTableType();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(admUserCode.plant.eq(plant));
        if (tableName != null && !tableName.isEmpty()) {
            builder.and(admUserCode.tableName.containsIgnoreCase(tableName));
        }
        if (description != null && !description.isEmpty()) {
            builder.and(admUserCode.description.containsIgnoreCase(description));
        }
        if (tableType != null && !tableType.isEmpty()) {
            builder.and(admUserCode.tableType.containsIgnoreCase(tableType));
        }

        return jpaQueryFactory
                .selectFrom(admUserCode)
                .where(builder)
                .fetch();
    }

    @Override
    public void deleteByPlantAndTableName(String plant, String tableName) {
        jpaQueryFactory
                .delete(admUserCodeData)
                .where(admUserCodeData.plant.eq(plant), admUserCodeData.tableName.eq(tableName))
                .execute();
    }
}
