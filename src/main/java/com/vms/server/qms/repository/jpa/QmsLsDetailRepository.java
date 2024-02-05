package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsLsDetail;
import com.vms.server.domain.entity.qms.id.QmsLsDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsLsDetailRepository extends JpaRepository<QmsLsDetail, QmsLsDetailId> {
        void deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber,String revisionNo);
}
