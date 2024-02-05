package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmAuthorityRole;
import com.vms.server.domain.entity.adm.id.AdmAuthorityRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAdmAuthorityRoleRepository extends JpaRepository<AdmAuthorityRole, AdmAuthorityRoleId> {
    List<AdmAuthorityRole> findByPlantAndRoleId(String plant, String roleId);

    List<AdmAuthorityRole> findByPlant(String plant);

    void deleteByPlantAndRoleId(String plant, String roleId);
}
