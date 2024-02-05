package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsKpiMaster;
import com.vms.server.domain.entity.qms.id.QmsKpiMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsQmsKpiMasterRepository extends JpaRepository<QmsKpiMaster, QmsKpiMasterId> {

}
