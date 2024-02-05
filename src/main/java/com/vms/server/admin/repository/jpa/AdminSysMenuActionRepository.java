package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.sys.SysMenuAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminSysMenuActionRepository extends JpaRepository<SysMenuAction, String> {
    List<SysMenuAction> findByActionSeq(String actionSeq);
}
