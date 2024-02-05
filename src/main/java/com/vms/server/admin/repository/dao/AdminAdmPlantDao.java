package com.vms.server.admin.repository.dao;

import com.vms.server.domain.vo.AdmPlantVo;

import java.util.List;

public interface AdminAdmPlantDao {

    List<AdmPlantVo> searchPlant(String likeColumn, String likeKeyword, String activePlant);
}
