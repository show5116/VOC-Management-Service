package com.vms.server.admin.repository.jpa;

import com.vms.server.domain.entity.sys.SysSystemCodeData;
import com.vms.server.domain.entity.sys.id.SysSystemCodeDataId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminSysSystemCodeDataRepository extends JpaRepository<SysSystemCodeData, SysSystemCodeDataId>  {

    List<SysSystemCodeData> findByPlantAndTableName(String plant, String tableName, Sort sort);

    List<SysSystemCodeData> findByPlantAndTableName(String plant, String tableName);

    List<SysSystemCodeData> findByPlantAndTableNameAndCodeName(String plant, String tableName, String codeName);

    List<SysSystemCodeData> findByPlantAndTableNameAndSpecialData1In(String plant, String tableName , List<String> siteList);

    List<SysSystemCodeData> findByPlantAndTableNameAndCodeGroup1(String plant, String tableName, String codeGroup1);

    List<SysSystemCodeData> findByPlantAndTableNameAndCodeNameNotOrderByCodeSeq(String plant, String tableName, String codeName);

    List<SysSystemCodeData> findByPlantAndTableNameOrderByCodeSeqAsc(String plant, String tableName);

    void deleteByPlantAndTableName(String plant, String tableName);
}
