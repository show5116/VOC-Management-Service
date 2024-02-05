package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.sys.SysMenuGroup;
import com.vms.server.domain.entity.sys.SysMenuGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminSysMenuGroupRepository extends JpaRepository<SysMenuGroup, SysMenuGroupId> {
    List<SysMenuGroup> findByPlantAndModuleId(String plant, String moduleId);
}
