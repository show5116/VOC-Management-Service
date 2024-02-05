package com.vms.server.qms.repository.querydsl.impl;

import static com.vms.server.domain.entity.qms.QQmsDocumentStatus.qmsDocumentStatus;
import static com.vms.server.domain.entity.sys.QSysSystemCodeData.sysSystemCodeData;
import com.vms.server.qms.repository.querydsl.QmsKpiResultQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class QmsKpiResultQueryRepositoryImpl implements QmsKpiResultQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<String> getDocNumber(String plant, String titleSearch) {


        var query = jpaQueryFactory
                .select(qmsDocumentStatus.qmsNumber)
                .from(qmsDocumentStatus, sysSystemCodeData)
                .where(qmsDocumentStatus.plant.eq(plant)
                        .and(qmsDocumentStatus.plant.eq(sysSystemCodeData.plant))
                        .and(sysSystemCodeData.tableName.eq("DOC_TYPE"))
                        .and(sysSystemCodeData.specialData2.eq("KPI"))
                        .and(qmsDocumentStatus.docType.eq(sysSystemCodeData.codeName)));

        if (titleSearch != null && !titleSearch.isEmpty()) {
            query.where(qmsDocumentStatus.docTitle.upper().like("%" + titleSearch.toUpperCase() + "%"));
        }

        return query.fetch();
    }
}
