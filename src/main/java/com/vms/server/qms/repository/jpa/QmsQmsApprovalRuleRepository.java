package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.qms.QmsApprovalRule;
import com.vms.server.domain.entity.qms.id.QmsApprovalRuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QmsQmsApprovalRuleRepository extends JpaRepository<QmsApprovalRule, QmsApprovalRuleId> {
    void deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(String plant, String systemName, String qmsNumber, String revisionNo);

    List<QmsApprovalRule> findByPlantAndSystemNameAndQmsNumberAndRevisionNoAndApprovalTypeAndUserId(String plant, String systemName, String qmsNumber, String revisionNo, String approvalType, String userId);


}
