package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsLsApplyRange;
import com.vms.server.domain.entity.qms.id.QmsLsApplyRangeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsLsApplyRangeRepository extends JpaRepository<QmsLsApplyRange, QmsLsApplyRangeId> {
    void deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber,String revisionNo);
}
