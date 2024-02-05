package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmSubcontractorQueryRepository;
import com.vms.server.domain.dto.AdmSubcontractorDto;
import com.vms.server.domain.entity.adm.AdmSubcontractor;
import com.vms.server.domain.entity.adm.QAdmSubcontractor;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class AdminAdmSubcontractorQueryRepositoryImpl implements AdminAdmSubcontractorQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmSubcontractor> getSupplierList(String plant, List<String> processes) {

        QAdmSubcontractor qAdmSubcontractor = QAdmSubcontractor.admSubcontractor;

        BooleanBuilder whereClause = new BooleanBuilder(qAdmSubcontractor.plant.eq(plant)
                .and(qAdmSubcontractor.ussageYn.eq("Y")));

        if (!processes.isEmpty()) {
            BooleanBuilder processClause = new BooleanBuilder();
            for (String process : processes) {
                StringPath field = Expressions.stringPath(qAdmSubcontractor, "is" + process);
                processClause.or(field.eq("Y"));
            }
            whereClause.and(processClause);
        }

        return jpaQueryFactory
                .select(Projections.fields(AdmSubcontractor.class,
                        qAdmSubcontractor.subctrtDesc, qAdmSubcontractor.subctrtCode))
                .distinct()
                .from(qAdmSubcontractor)
                .where(whereClause)
                .orderBy(qAdmSubcontractor.subctrtDesc.asc())
                .fetch();
    }

    @Override
    public List<AdmSubcontractor> getSupplierList(String plant, String processes) {

        QAdmSubcontractor qAdmSubcontractor = QAdmSubcontractor.admSubcontractor;

        BooleanBuilder whereClause = new BooleanBuilder(qAdmSubcontractor.plant.eq(plant)
                .and(qAdmSubcontractor.ussageYn.eq("Y")));

        if (processes != null & !processes.isEmpty()) {
            whereClause.and(qAdmSubcontractor.subctrtCode.eq(processes));
        }

        return jpaQueryFactory
                .select(Projections.fields(AdmSubcontractor.class,
                        qAdmSubcontractor.subctrtDesc, qAdmSubcontractor.subctrtCode))

                .from(qAdmSubcontractor)
                .where(whereClause)
                .orderBy(qAdmSubcontractor.subctrtDesc.desc())
                .fetch();
    }

    @Override
    public List<AdmSubcontractor> getSite(String plant, String facility) {

        QAdmSubcontractor qAdmSubcontractor = QAdmSubcontractor.admSubcontractor;

        BooleanBuilder whereClause = new BooleanBuilder(qAdmSubcontractor.plant.eq(plant)
                .and(qAdmSubcontractor.ussageYn.eq("Y")));

        if (facility != null && !facility.isEmpty()) {
                StringPath field = Expressions.stringPath(qAdmSubcontractor, "is" + facility);
            whereClause.or(field.eq("Y"));
        }

        return  jpaQueryFactory
                .select(Projections.fields(AdmSubcontractor.class,qAdmSubcontractor.subctrtDesc,
                        qAdmSubcontractor.subctrtCode))
                .from(qAdmSubcontractor)
                .where(whereClause)
                .orderBy(qAdmSubcontractor.subctrtDesc.asc())
                .fetch();
    }

    @Override
    public List<AdmSubcontractor> getAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto) {
        String plant = admSubcontractorDto.getPlant();
        String subctrtCode = admSubcontractorDto.getSubctrtCode();
        String subctrtDesc = admSubcontractorDto.getSubctrtDesc();

        QAdmSubcontractor admSubcontractor = QAdmSubcontractor.admSubcontractor;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(admSubcontractor.plant.eq(plant));
        if(subctrtCode != null && subctrtCode != "") {
            builder.and(admSubcontractor.subctrtCode.containsIgnoreCase(subctrtCode));
        }
        if(subctrtDesc != null && subctrtDesc != "") {
            builder.and(admSubcontractor.subctrtDesc.containsIgnoreCase(subctrtDesc));
        }

        return jpaQueryFactory
                .select(admSubcontractor)
                .from(admSubcontractor)
                .where(builder)
                .orderBy(admSubcontractor.subctrtCode.asc())
                .fetch();
    }
}
