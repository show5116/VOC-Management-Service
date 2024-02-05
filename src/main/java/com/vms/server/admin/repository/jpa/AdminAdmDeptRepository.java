package com.vms.server.admin.repository.jpa;


import com.vms.server.domain.entity.adm.AdmDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAdmDeptRepository extends JpaRepository<AdmDept, String> {

    AdmDept findByDeptId(String deptId);

    List<AdmDept> findByDeptName(String deptName);
    List<AdmDept> findAllByDeptStatus(String deptStatus);

    AdmDept findByUpDeptIdx(String upDeptIdx);

}
