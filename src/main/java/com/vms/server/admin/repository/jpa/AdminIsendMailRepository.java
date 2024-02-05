package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.etc.Isendmail;
import com.vms.server.domain.entity.etc.id.IsendmailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminIsendMailRepository  extends JpaRepository<Isendmail, IsendmailId> {

    List<Isendmail> findBySendFlag(int sendFlag);
}
