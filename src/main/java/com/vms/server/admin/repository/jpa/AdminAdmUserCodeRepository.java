package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmUserCode;
import com.vms.server.domain.entity.adm.id.AdmUserCodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAdmUserCodeRepository extends JpaRepository<AdmUserCode, AdmUserCodeId> {
}
