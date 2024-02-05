package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminQmsAttachedFileQueryRepository;
import com.vms.server.domain.entity.qms.QQmsAttachFile;
import com.vms.server.domain.vo.QmsAttachFileVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.vms.server.domain.entity.qms.QQmsAttachFile.qmsAttachFile;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminQmsAttachedFileQueryRepositoryImpl implements AdminQmsAttachedFileQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QmsAttachFileVo> getSearchAllAttachedFile(String plant) {

        return jpaQueryFactory.select(Projections.fields(QmsAttachFileVo.class, qmsAttachFile.systemName,
                        qmsAttachFile.qmsNumber,
                        qmsAttachFile.revisionNo,
                        qmsAttachFile.systemNameMtype,
                        qmsAttachFile.systemNameStype,
                        qmsAttachFile.orgFileId,
                        qmsAttachFile.filePath))
                .from(qmsAttachFile)
                .where(qmsAttachFile.plant.eq(plant)
                        .and(qmsAttachFile.filePath.isNotNull()))
                .orderBy(qmsAttachFile.systemName.asc(),
                        qmsAttachFile.qmsNumber.asc(),
                        qmsAttachFile.fileSeq.asc())
                .fetch();
    }
}
