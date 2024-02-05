package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.querydsl.AdminQmsAttachedFileQueryRepository;
import com.vms.server.admin.service.QmsFileCheckService;
import com.vms.server.domain.vo.QmsAttachFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QmsFileCheckServiceImpl implements QmsFileCheckService {
    private final AdminQmsAttachedFileQueryRepository adminQmsAttachedFileQueryRepository;
    @Override
    public List<QmsAttachFileVo> getAttachedFileList(String plant) {
        return adminQmsAttachedFileQueryRepository.getSearchAllAttachedFile(plant);
    }
}
