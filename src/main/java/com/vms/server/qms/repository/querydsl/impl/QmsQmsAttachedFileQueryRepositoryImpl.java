package com.vms.server.qms.repository.querydsl.impl;

import com.vms.server.domain.dto.QmsAttachFileDto;
import static com.vms.server.domain.entity.qms.QQmsAttachFile.qmsAttachFile;
import com.vms.server.qms.repository.querydsl.QmsQmsAttachedFileQueryRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QmsQmsAttachedFileQueryRepositoryImpl implements QmsQmsAttachedFileQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<QmsAttachFileDto> getLoadAttachedFile(String plant, String systemName, String qmsNumber, String systemNameType) {


        return  jpaQueryFactory
                .select(Projections.fields(QmsAttachFileDto.class,
                        qmsAttachFile.orgFileId, // fileName
                        qmsAttachFile.filePath, // filePath
                        qmsAttachFile.expandField1,
                        qmsAttachFile.fileRemark, // remark
                        qmsAttachFile.filePath.as("filePathR"))) // filePathR
                .from(qmsAttachFile)
                .where(qmsAttachFile.plant.eq(plant)
                        .and(qmsAttachFile.systemName.eq(systemName))
                        .and(qmsAttachFile.qmsNumber.eq(qmsNumber))
                        .and(qmsAttachFile.systemNameMtype.eq(systemNameType)))
                .fetch();
    }
}
