package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsMrbMaster;
import com.vms.server.domain.entity.qms.id.QmsMrbMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsQmsMrbMasterRepository extends JpaRepository<QmsMrbMaster, QmsMrbMasterId> {

    QmsMrbMaster findByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);

    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);
}
