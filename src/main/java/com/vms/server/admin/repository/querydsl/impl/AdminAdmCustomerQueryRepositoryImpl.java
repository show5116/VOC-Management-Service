package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminAdmCustomerQueryRepository;
import com.vms.server.domain.entity.adm.AdmCustomer;
import com.vms.server.domain.entity.adm.QAdmCustomer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminAdmCustomerQueryRepositoryImpl implements AdminAdmCustomerQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdmCustomer> getCustomerList(String plant) {
        QAdmCustomer qAdmCustomer = QAdmCustomer.admCustomer;

        return jpaQueryFactory
                .select(Projections.fields(AdmCustomer.class,
                qAdmCustomer.customerName,
                qAdmCustomer.customer))
                .distinct()
                .from(qAdmCustomer)
                .where(qAdmCustomer.plant.eq(plant))
                .orderBy(qAdmCustomer.customer.asc())
                .fetch();
    }

    @Override
    public List<AdmCustomer> getAdmCustomer(String plant, String customerCode, String customerName) {
        QAdmCustomer admCustomer = QAdmCustomer.admCustomer;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(admCustomer.plant.eq(plant));
        if (customerCode != null && !customerCode.isEmpty()) {
            builder.and(admCustomer.customer.containsIgnoreCase(customerCode));
        }
        if (customerName != null && !customerName.isEmpty()) {
            builder.and(admCustomer.customerName.containsIgnoreCase(customerName));
        }

        return jpaQueryFactory
                .select(admCustomer)
                .from(admCustomer)
                .where(builder)
                .fetch();
    }
}
