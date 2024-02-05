package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsSelectedList;
import com.vms.server.domain.entity.qms.id.QmsSelectedListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsSelectedListRepository extends JpaRepository<QmsSelectedList, QmsSelectedListId> {
    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(String plant, String systemName, String qmsNumber, String revisionNo, String ItemType);

    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);
}
