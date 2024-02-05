package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsKpiList;
import com.vms.server.domain.entity.qms.id.QmsKpiListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsQmsKpiListRepository extends JpaRepository<QmsKpiList, QmsKpiListId> {
}
