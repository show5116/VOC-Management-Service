package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsActionItemList;
import com.vms.server.domain.entity.qms.id.QmsActionItemListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QmsQmsActionItemListRepository extends JpaRepository<QmsActionItemList, QmsActionItemListId> {
    List<QmsActionItemList> findByPlantAndSystemNameAndQmsNumberOrderBySeqNo(String plant, String systemName, String qmsNumber);

    void deleteAllByPlantAndSystemNameAndQmsNumber(String plant, String systemName, String qmsNumber);


}
