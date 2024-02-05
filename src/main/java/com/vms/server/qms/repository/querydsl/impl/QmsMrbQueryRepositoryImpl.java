package com.vms.server.qms.repository.querydsl.impl;

import com.vms.server.domain.dto.CommonUserInfoDto;
import static com.vms.server.domain.entity.adm.QAdmDept.admDept;
import static com.vms.server.domain.entity.adm.QAdmEmployee.admEmployee;
import static com.vms.server.domain.entity.adm.QAdmMyJob.admMyJob;
import static com.vms.server.domain.entity.adm.QAdmPosition.admPosition;
import static com.vms.server.domain.entity.qms.QQmsSelectedList.qmsSelectedList;
import com.vms.server.qms.repository.querydsl.QmsMrbQueryRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QmsMrbQueryRepositoryImpl implements QmsMrbQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<CommonUserInfoDto> selectedMemberList(String plant, String systemName, String qmsNumber, String revisionNo) {


        return jpaQueryFactory
                .select(Projections.fields(CommonUserInfoDto.class,
                        qmsSelectedList.itemCode.as("userid"),
                        admEmployee.empName.as("username"),
                        admDept.deptName.as("department"),
                        admPosition.posName.as("description")))
                .from(qmsSelectedList)
                .join(admEmployee).on(qmsSelectedList.itemCode.eq(admEmployee.empCode))
                .join(admMyJob).on(admEmployee.empCode.eq(admMyJob.empCode))
                .join(admDept).on(admMyJob.deptId.eq(admDept.deptId))
                .join(admPosition).on(admEmployee.posId.eq(admPosition.posId))
                .where(qmsSelectedList.plant.eq(plant)
                        .and(qmsSelectedList.systemName.eq(systemName))
                        .and(qmsSelectedList.qmsNumber.eq(qmsNumber))
                        .and(qmsSelectedList.revisionNo.eq(revisionNo))
                        .and(qmsSelectedList.itemType.eq("MEMBER")))
                .fetch();
    }
}
