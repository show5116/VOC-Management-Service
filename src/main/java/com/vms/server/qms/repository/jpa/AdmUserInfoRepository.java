package com.vms.server.qms.repository.jpa;

import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.vms.server.domain.entity.adm.id.AdmUserInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmUserInfoRepository extends JpaRepository<AdmUserInfo , AdmUserInfoId> {
    List<AdmUserInfo> findByUserId(String userId);

    List<AdmUserInfo> findByRoleId(String roleId);

    Optional<AdmUserInfo>  findByUserIdAndPlantAndAdmission(String userId, String plant, String admission);

    Optional<AdmUserInfo> findByUserIdAndPlant(String userId, String plant);
}