package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.sys.SysChangeDrafterLog;
import com.vms.server.domain.entity.sys.id.SysChangeDrafterLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QmsSysChangeDrafterLogRepository extends JpaRepository<SysChangeDrafterLog, SysChangeDrafterLogId> {


}
