package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmEmployeeRepository extends JpaRepository<AdmEmployee, String> {

    AdmEmployee findByUserId(String userId);

}
