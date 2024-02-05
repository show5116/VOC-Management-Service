package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmMyJob;
import com.vms.server.domain.entity.adm.id.AdmMyJobId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmJobRepository extends JpaRepository<AdmMyJob, AdmMyJobId> {
}
