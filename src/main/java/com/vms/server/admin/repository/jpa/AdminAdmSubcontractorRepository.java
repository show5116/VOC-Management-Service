package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmSubcontractor;
import com.vms.server.domain.entity.adm.id.AdmSubcontractorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmSubcontractorRepository extends JpaRepository<AdmSubcontractor, AdmSubcontractorId> {

}
