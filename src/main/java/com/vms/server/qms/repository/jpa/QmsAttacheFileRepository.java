package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QmsAttacheFileRepository extends JpaRepository<QmsAttachFile, QmsAttachFileId> {
    QmsAttachFile findFirstByRealFileId(String fileId);
}
