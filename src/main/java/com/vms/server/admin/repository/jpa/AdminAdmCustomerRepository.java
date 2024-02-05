package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmCustomer;
import com.vms.server.domain.entity.adm.id.AdmCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmCustomerRepository extends JpaRepository<AdmCustomer, AdmCustomerId> {

}
