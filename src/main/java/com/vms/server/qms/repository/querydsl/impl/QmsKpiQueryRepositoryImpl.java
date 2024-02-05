package com.vms.server.qms.repository.querydsl.impl;

import com.vms.server.domain.dto.QmsDocumentStatusDto;
import static com.vms.server.domain.entity.qms.QQmsDocumentStatus.qmsDocumentStatus;
import static com.vms.server.domain.entity.qms.QQmsKpiMaster.qmsKpiMaster;
import static com.vms.server.domain.entity.sys.QSysSystemCodeData.sysSystemCodeData;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import com.vms.server.qms.repository.querydsl.QmsKpiQueryRespository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QmsKpiQueryRepositoryImpl implements QmsKpiQueryRespository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SysSystemCodeDataVo> getRegYearList(String plant, String systemName) {

        return jpaQueryFactory.select(Projections.fields(SysSystemCodeDataVo.class, qmsKpiMaster.regYear.as("description"),
                        qmsKpiMaster.regYear.as("code")))
                .from(qmsKpiMaster)
                .where(qmsKpiMaster.plant.eq(plant),
                        qmsKpiMaster.systemName.eq(systemName))
                .groupBy(qmsKpiMaster.regYear)
                .orderBy(qmsKpiMaster.regYear.asc())
                .fetch();
    }

    @Override
    public List<QmsDocumentStatusDto> getDocName(String plant, String titleSearch) {

        BooleanBuilder whereClause = new BooleanBuilder(qmsDocumentStatus.plant.eq(plant).and(
                qmsDocumentStatus.plant.eq(sysSystemCodeData.plant)).and(
                sysSystemCodeData.tableName.eq("DOC_TYPE")).and(
                sysSystemCodeData.specialData2.eq("KPI")).and(
                qmsDocumentStatus.docType.eq(sysSystemCodeData.codeName)));

        if (titleSearch != null && !titleSearch.isEmpty()) {
            whereClause.and(qmsDocumentStatus.docTitle.upper().like("%" + titleSearch.toUpperCase() + "%"));
        }

        return jpaQueryFactory.select(Projections.fields(QmsDocumentStatusDto.class, qmsDocumentStatus.docTitle,
                qmsDocumentStatus.qmsNumber))
                .from(qmsDocumentStatus, sysSystemCodeData)
                .where(whereClause).fetch();
    }
}
