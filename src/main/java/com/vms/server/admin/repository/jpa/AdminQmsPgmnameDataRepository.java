package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.qms.QmsPgmnameData;
import com.vms.server.domain.entity.qms.id.QmsPgmnameDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminQmsPgmnameDataRepository extends JpaRepository<QmsPgmnameData, QmsPgmnameDataId> {

    List<QmsPgmnameData> findByPlantAndDeviceIn(String plant, List<String> Devices);
    List<QmsPgmnameData> findByPlant(String plant);
}
