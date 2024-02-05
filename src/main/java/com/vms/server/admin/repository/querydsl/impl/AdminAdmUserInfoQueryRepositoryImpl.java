package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmUserInfoQueryRepository;
import com.vms.server.domain.dto.AdmUserInfoDto;
import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vms.server.domain.entity.adm.QAdmUserInfo.admUserInfo;

@Repository
@RequiredArgsConstructor
public class AdminAdmUserInfoQueryRepositoryImpl implements AdminAdmUserInfoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmUserInfo> getAdmUserInfo(AdmUserInfoDto admUserInfoDto) {
        String plant = admUserInfoDto.getPlant();

        return jpaQueryFactory
                .select(admUserInfo)
                .from(admUserInfo)
                .where(admUserInfo.plant.eq(plant))
                .orderBy(admUserInfo.userName.asc())
                .fetch();
    }
}
