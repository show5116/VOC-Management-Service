package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAdmPositionRepository extends JpaRepository<AdmPosition, String> {
}
