package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmUserCodeData;
import com.vms.server.domain.entity.adm.id.AdmUserCodeDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAdmUserCodeDataRepository extends JpaRepository<AdmUserCodeData, AdmUserCodeDataId> {
    List<AdmUserCodeData> findByPlantAndTableNameOrderByCodeSeqAsc(String plant, String tableName);
}
