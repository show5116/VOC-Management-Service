package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.qms.QmsSelectedList;
import com.vms.server.domain.entity.qms.id.QmsSelectedListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminQmsSelectedListRepository extends JpaRepository<QmsSelectedList, QmsSelectedListId> {

    List<QmsSelectedList> findByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(String plant, String systemName, String qmsNumber, String revisionNo, String itemType);
}
