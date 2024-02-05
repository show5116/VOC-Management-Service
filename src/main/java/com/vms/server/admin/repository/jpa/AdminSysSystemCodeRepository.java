package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.sys.SysSystemCode;
import com.vms.server.domain.entity.sys.SysSystemCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSysSystemCodeRepository extends JpaRepository<SysSystemCode, SysSystemCodeId> {

}
