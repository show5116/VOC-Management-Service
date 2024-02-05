package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmRoleManagerQueryRepository;
import com.vms.server.domain.vo.AdmAuthorityRoleVo;
import com.vms.server.domain.vo.AdmRoleGroupVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.vms.server.domain.entity.adm.QAdmRoleGroup.admRoleGroup;
import static com.vms.server.domain.entity.adm.QAdmAuthorityRole.admAuthorityRole;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminAdmRoleManagerQueryRepositoryImpl implements AdminAdmRoleManagerQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmRoleGroupVo> searchRoleMenuGroup(String plant, String searchOrder, String searchKeyword) {

        BooleanBuilder whereClause = new BooleanBuilder(admRoleGroup.roleType.eq("M"));


        if (plant != null && !plant.isEmpty()) {
            BooleanBuilder processClause = new BooleanBuilder();
            processClause.and(admRoleGroup.plant.eq(plant));

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                StringPath field = Expressions.stringPath(admRoleGroup,  searchOrder.toUpperCase());
                processClause.and(field.like("%" + searchKeyword.trim().toUpperCase() + "%"));
            }

            whereClause.and(processClause);
        } else {
            BooleanBuilder processClause = new BooleanBuilder();

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                StringPath field = Expressions.stringPath(admRoleGroup,  searchOrder.toUpperCase());
                processClause.and(field.like("%" + searchKeyword.trim().toUpperCase() + "%"));
            }
            whereClause.and(processClause);
        }
        return jpaQueryFactory.select(Projections.fields(AdmRoleGroupVo.class,
                        admRoleGroup.plant,
                        admRoleGroup.roleGroup.as("menuGroup"),
                        admRoleGroup.description))
                .from(admRoleGroup)
                .where(whereClause)
                .orderBy(admRoleGroup.plant.asc(), admRoleGroup.roleGroup.asc()).fetch();
    }

    @Override
    public List<AdmAuthorityRoleVo> searchAuthorityRole(String plant, String searchOrder, String searchKeyword) {
        BooleanBuilder whereClause = new BooleanBuilder();


        if (plant != null && !plant.isEmpty()) {
            BooleanBuilder processClause = new BooleanBuilder();
            processClause.and(admAuthorityRole.plant.eq(plant));

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                StringPath field = Expressions.stringPath(admAuthorityRole,  searchOrder.toUpperCase());
                processClause.and(field.like("%" + searchKeyword.trim().toUpperCase() + "%"));
            }
            whereClause.and(processClause);
        } else {
            BooleanBuilder processClause = new BooleanBuilder();

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                StringPath field = Expressions.stringPath(admAuthorityRole,  searchOrder.toUpperCase());
                processClause.and(field.like("%" + searchKeyword.trim().toUpperCase() + "%"));
            }
            whereClause.and(processClause);
        }

        return  jpaQueryFactory.select(Projections.fields(AdmAuthorityRoleVo.class,
                        admAuthorityRole.plant,
                        admAuthorityRole.roleId.as("roleGroup"),
                        admAuthorityRole.description,
                        admAuthorityRole.operationGroup.as("operGroup"),
                        admAuthorityRole.mesMenuGroup.as("menuGroup")
                        ))
                .from(admAuthorityRole)
                .where(whereClause)
                .orderBy(admAuthorityRole.plant.asc(), admAuthorityRole.roleId.asc()).fetch();
    }
}
