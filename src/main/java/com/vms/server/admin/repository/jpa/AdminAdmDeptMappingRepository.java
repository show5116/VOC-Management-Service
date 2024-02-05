package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmDeptMapping;
import com.vms.server.domain.entity.adm.id.AdmDeptMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmDeptMappingRepository extends JpaRepository<AdmDeptMapping, AdmDeptMappingId> {

    void deleteByPlantAndDeptBuAndDeptId(String plant, String deptBu, String deptId);

}
