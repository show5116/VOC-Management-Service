package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.adm.AdmPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAdmPlantRepository extends JpaRepository<AdmPlant, String> {
    List<AdmPlant> findByActivePlant(String isActive);
}
