package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsMrbLotList;
import com.vms.server.domain.entity.qms.id.QmsMrbLotListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QmsQmsMrbLotListRepository extends JpaRepository<QmsMrbLotList, QmsMrbLotListId> {
    List<QmsMrbLotList> findByPlantAndSystemNameAndQmsNumber(String plant, String systemName, String qmsNumber);

    void deleteAllByPlantAndSystemNameAndQmsNumber(String plant, String systemName, String qmsNumber);

    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);
}
