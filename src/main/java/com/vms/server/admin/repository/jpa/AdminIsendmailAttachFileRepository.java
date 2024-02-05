package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.etc.IsendmailAttachFile;
import com.vms.server.domain.entity.etc.id.IsendmailAttachFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminIsendmailAttachFileRepository extends JpaRepository<IsendmailAttachFile, IsendmailAttachFileId> {

    List<IsendmailAttachFile> findBySeqAndSystemName(String seq, String systemName);
}
