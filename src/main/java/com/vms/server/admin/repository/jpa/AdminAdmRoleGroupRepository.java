package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmRoleGroup;
import com.vms.server.domain.entity.adm.id.AdmRoleGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAdmRoleGroupRepository extends JpaRepository<AdmRoleGroup, AdmRoleGroupId> {

    void deleteByPlantAndRoleGroup(String plant, String roleGroup);

    AdmRoleGroup findByPlantAndRoleGroup(String plant, String roleGroup);

    List<AdmRoleGroup> findByPlantAndRoleTypeOrderByRoleGroup(String plant, String roleTpe);

    List<AdmRoleGroup> findByPlant(String plant);
}
