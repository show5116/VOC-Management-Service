package com.vms.server.admin.service;

import com.vms.server.domain.vo.QmsAttachFileVo;

import java.util.List;

public interface QmsFileCheckService {

    List<QmsAttachFileVo> getAttachedFileList(String plant);

}

