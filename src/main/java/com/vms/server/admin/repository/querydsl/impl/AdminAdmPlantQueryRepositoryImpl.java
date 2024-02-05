package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmPlantQueryRepository;
import com.vms.server.domain.dto.AdmPlantDto;
import com.vms.server.domain.entity.adm.AdmPlant;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vms.server.domain.entity.adm.QAdmPlant.admPlant;

@Repository
@RequiredArgsConstructor
public class AdminAdmPlantQueryRepositoryImpl implements AdminAdmPlantQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmPlant> getAdmPlant(AdmPlantDto admPlantDto) {
        String plant = admPlantDto.getPlant();
        String description = admPlantDto.getDescription();

        BooleanBuilder builder = new BooleanBuilder();
        if (plant != null && !plant.isEmpty()) {
            builder.and(admPlant.plant.containsIgnoreCase(plant));
        }
        if (description != null && !description.isEmpty()) {
            builder.and(admPlant.description.containsIgnoreCase(description));
        }

        return jpaQueryFactory
                .selectFrom(admPlant)
                .where(builder)
                .fetch();
    }
}
