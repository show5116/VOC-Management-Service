package com.vms.server.admin.service;

import com.vms.server.domain.vo.AttachedFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    String saveFileTemp(File file, String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo)throws Exception ;
    void saveAttachFile(MultipartFile multipartFile, String plant, String qmsNumber, String systemName, String sysMType, String sysSType, String revisionNo, String fileDesc) throws Exception ;

    void deleteFiles(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo ) throws Exception;

    AttachedFileVo getAttachedFile(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo, int fileSeq);

    List<AttachedFileVo> getAttachedFiles(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo);
}
