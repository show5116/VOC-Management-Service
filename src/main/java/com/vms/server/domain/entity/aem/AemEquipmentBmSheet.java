package com.vms.server.domain.entity.aem;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "AEM_EQUIPMENT_BM_SHEET")
public class AemEquipmentBmSheet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Column(name = "AREA")
  public String area;
  @Column(name = "UGROUP")
  public String ugroup;
  @Column(name = "MODELNAME")
  public String modelname;
  @Column(name = "EVENT")
  public String event;
  @Column(name = "DOWNCODE")
  public String downcode;
  @Column(name = "YYMMDD")
  public String yymmdd;
  @Column(name = "CALL_TIME")
  public java.sql.Date callTime;
  @Column(name = "START_TIME")
  public java.sql.Date startTime;
  @Column(name = "TEST_TIME")
  public java.sql.Date testTime;
  @Column(name = "END_TIME")
  public java.sql.Date endTime;
  @Column(name = "UP_TIME")
  public java.sql.Date upTime;
  @Column(name = "MACHINER")
  public String machiner;
  @Column(name = "UNIT_CODE")
  public String unitCode;
  @Column(name = "ERR_UNIT")
  public String errUnit;
  @Column(name = "PART_CODE")
  public String partCode;
  @Column(name = "ERR_PART")
  public String errPart;
  @Column(name = "STATE_CODE")
  public String stateCode;
  @Column(name = "ERR_STATE")
  public String errState;
  @Column(name = "FACTOR_CODE")
  public String factorCode;
  @Column(name = "ERR_FACTOR")
  public String errFactor;
  @Column(name = "STATUS_CODE")
  public String statusCode;
  @Column(name = "ERR_STATUS")
  public String errStatus;
  @Column(name = "REPAIR_CODE")
  public String repairCode;
  @Column(name = "ERR_REPAIR")
  public String errRepair;
  @Column(name = "LOSS_QTY")
  public String lossQty;
  @Column(name = "REPAIR_TIME")
  public String repairTime;
  @Column(name = "CUM_STIME")
  public String cumStime;
  @Column(name = "CUM_TTIME")
  public String cumTtime;
  @Column(name = "CUM_ETIME")
  public String cumEtime;
  @Column(name = "GRADE")
  public String grade;
  @Column(name = "REPAIR_FLAG")
  public String repairFlag;
  @Column(name = "REGISTER_FLAG")
  public String registerFlag;
  @Column(name = "SHIFT")
  public String shift;
  @Column(name = "IMAGE_NAME")
  public String imageName;
  @Column(name = "PART_USE_FLAG")
  public String partUseFlag;
  @Column(name = "FLAG_1")
  public String flag1;
  @Column(name = "FLAG_2")
  public String flag2;
  @Column(name = "FLAG_3")
  public String flag3;
  @Column(name = "DOWN_NAME")
  public String downName;
}
