package com.vms.server.qms.repository.querydsl;

import com.vms.server.domain.dto.QmsAttachFileDto;

import java.util.List;

public interface QmsQmsAttachedFileQueryRepository {
    List<QmsAttachFileDto> getLoadAttachedFile(String plant, String systemName, String qmsNumber, String systemNameType);
}
