package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsCipStatus;
import com.vms.server.domain.entity.qms.id.QmsCipStatusId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QmsQmsCipStatusRepository extends JpaRepository<QmsCipStatus, QmsCipStatusId> {
    QmsCipStatus findByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);

    void deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);

}
