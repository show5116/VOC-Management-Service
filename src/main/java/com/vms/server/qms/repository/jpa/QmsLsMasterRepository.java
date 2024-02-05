package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsLsMaster;
import com.vms.server.domain.entity.qms.id.QmsLsMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsLsMasterRepository extends JpaRepository<QmsLsMaster, QmsLsMasterId> {

}
