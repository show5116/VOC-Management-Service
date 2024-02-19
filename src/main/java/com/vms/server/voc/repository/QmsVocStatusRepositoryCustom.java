package com.vms.server.voc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vms.server.domain.entity.qms.QQmsAttachFile;
import com.vms.server.voc.dto.response.QVocListResponse;
import com.vms.server.voc.dto.response.VocListResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.vms.server.domain.entity.qms.QQmsAttachFile.qmsAttachFile;
import static com.vms.server.domain.entity.qms.QQmsVocStatus.qmsVocStatus;

public interface QmsVocStatusRepositoryCustom {

    List<VocListResponse> findVocList();

    @RequiredArgsConstructor
    public class QmsVocStatusRepositoryCustomImpl implements QmsVocStatusRepositoryCustom {
        private final JPAQueryFactory jpaQueryFactory;

        @Override
        public List<VocListResponse> findVocList() {
            return jpaQueryFactory.select(new QVocListResponse(
                            qmsVocStatus.id.plant,
                            qmsVocStatus.id.systemName,
                            qmsVocStatus.id.qmsNumber,
                            qmsVocStatus.issueDate,
                            qmsVocStatus.customer,
                            qmsVocStatus.classification,
                            qmsVocStatus.requiredResponseDate,
                            qmsVocStatus.remark,
                            qmsVocStatus.closedDate,
                            qmsVocStatus.closedUser,
                            qmsVocStatus.personInCharge,
                            qmsVocStatus.requirement,
                            qmsVocStatus.updateDate,
                            qmsVocStatus.updateUser,
                            qmsAttachFile.realFileId,
                            qmsAttachFile.orgFileId
                    ))
                    .from(qmsVocStatus)
                    .innerJoin(qmsAttachFile)
                    .on(qmsVocStatus.id.qmsNumber.eq(qmsAttachFile.id.qmsNumber))
                    .fetch();
        }
    }

}
