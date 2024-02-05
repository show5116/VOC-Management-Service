package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.sys.SysMenuStructure;
import com.vms.server.domain.entity.sys.id.SysMenuStructureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuStructureRepository extends JpaRepository<SysMenuStructure, SysMenuStructureId> {
    void deleteAllByPlantAndGroupIdAndModuleId(String plant, String groupId, String moduleId);
}
