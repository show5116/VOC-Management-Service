package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.vo.QmsAttachFileVo;

import java.util.List;

public interface AdminQmsAttachedFileQueryRepository {

    List<QmsAttachFileVo> getSearchAllAttachedFile(String plant);

}
