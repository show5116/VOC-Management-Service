package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsKpiResultStatus;
import com.vms.server.domain.entity.qms.id.QmsKpiResultStatusId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QmsQmsKpiResultStatusRepository extends JpaRepository<QmsKpiResultStatus, QmsKpiResultStatusId> {

    List<QmsKpiResultStatus> findByPlantAndSystemNameAndRegDateAndTypeAndDeptBuAndFunctionType(String plant, String systemName, String regYear, String type, String deptBu, String functionType);

    List<QmsKpiResultStatus> findByPlantAndSystemNameAndRegDateAndTypeAndProcessAndSubcontract(String plant, String systemName, String regYear, String type, String process, String subcontract);
}
