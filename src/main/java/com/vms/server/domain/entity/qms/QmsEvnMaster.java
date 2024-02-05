package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsEvnMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsEvnMasterId.class)
@Table(name = "QMS_EVN_MASTER")
public class QmsEvnMaster {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "SUPPLIER")
  public String supplier;
  @Column(name = "MATERIAL_CATEGORY")
  public String materialCategory;
  @Column(name = "MATERIAL_SUPPLIER")
  public String materialSupplier;
  @Column(name = "MATERIAL_NAME")
  public String materialName;
  @Column(name = "MATERIAL_TYPE")
  public String materialType;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "MAT_ALARM_FLAG")
  public String matAlarmFlag;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
