package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QmsAttacheFileRepository extends JpaRepository<QmsAttachFile, QmsAttachFileId> {
    void deleteAllByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(String plant, String systemName, String sysMType, String sysSType, String qmsNumber, String revisionNo);

    void deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(String plant, String systemName, String qmsNumber, String systemNameMtype, String revisionNo);

    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);

    List<QmsAttachFile> findByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo);

    QmsAttachFile findByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNoAndFileSeq(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo, int fileSeq);
}
