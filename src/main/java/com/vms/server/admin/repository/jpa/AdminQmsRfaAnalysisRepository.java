package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.qms.QmsRfaAnalysis;
import com.vms.server.domain.entity.qms.id.QmsRfaAnalysisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminQmsRfaAnalysisRepository extends JpaRepository<QmsRfaAnalysis, QmsRfaAnalysisId> {
    List<QmsRfaAnalysis> findByPlantAndSystemNameAndQmsNumber(String plant, String systemName, String qmsNumber);

}
