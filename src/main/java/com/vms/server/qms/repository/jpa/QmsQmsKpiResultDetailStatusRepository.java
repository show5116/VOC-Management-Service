package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsKpiResultDetailStatus;
import com.vms.server.domain.entity.qms.id.QmsKpiResultDetailStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsQmsKpiResultDetailStatusRepository  extends JpaRepository<QmsKpiResultDetailStatus, QmsKpiResultDetailStatusId> {

}
