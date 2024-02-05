package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminQmsAttachFileRepository extends JpaRepository<QmsAttachFile, QmsAttachFileId> {

    List<QmsAttachFile> findByPlantAndSystemName(String plant, String systemName);

}
