package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmOperationId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmOperationId.class)
@Table(name = "ADM_OPERATION")
public class AdmOperation {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "OPERATION")
  public String operation;
  @Column(name = "SHORT_DESC")
  public String shortDesc;
  @Column(name = "LONG_DESC")
  public String longDesc;
  @Column(name = "SHIPPING_FLAG")
  public String shippingFlag;
  @Column(name = "STORE_FLAG")
  public String storeFlag;
  @Column(name = "STORE_TYPE")
  public String storeType;
  @Column(name = "STOCKER_FLAG")
  public String stockerFlag;
  @Column(name = "INTRANSIT")
  public String intransit;
  @Column(name = "CHANGE_ROUTESET")
  public String changeRouteset;
  @Column(name = "REQUEST_QA")
  public String requestQa;
  @Column(name = "QA_TYPE")
  public String qaType;
  @Column(name = "EXTERNAL_FLAG")
  public String externalFlag;
  @Column(name = "PRINT_LABEL")
  public String printLabel;
  @Column(name = "PASS_FLAG")
  public String passFlag;
  @Column(name = "MATERIAL_USAGE")
  public String materialUsage;
  @Column(name = "QTY_1ST_UNIT")
  public String qty1StUnit;
  @Column(name = "QTY_2ND_UNIT")
  public String qty2NdUnit;
  @Column(name = "PROCESS_STEP")
  public String processStep;
  @Column(name = "BATCH_PROCESS")
  public String batchProcess;
  @Column(name = "MULTI_EQUIPMENT")
  public String multiEquipment;
  @Column(name = "LOSS_TABLE")
  public String lossTable;
  @Column(name = "BONUS_TABLE")
  public String bonusTable;
  @Column(name = "REWORK_TABLE")
  public String reworkTable;
  @Column(name = "CV_TABLE")
  public String cvTable;
  @Column(name = "REPAIR_TABLE")
  public String repairTable;
  @Column(name = "IN_PARAMETERSET")
  public String inParameterset;
  @Column(name = "OUT_PARAMETERSET")
  public String outParameterset;
  @Column(name = "REWORK_PARAMETERSET")
  public String reworkParameterset;
  @Column(name = "ADHOC_PARAMETERSET")
  public String adhocParameterset;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "EXPAND_FLAG1")
  public String expandFlag1;
  @Column(name = "EXPAND_FLAG2")
  public String expandFlag2;
  @Column(name = "EXPAND_FLAG3")
  public String expandFlag3;
  @Column(name = "EXPAND_FLAG4")
  public String expandFlag4;
  @Column(name = "EXPAND_FLAG5")
  public String expandFlag5;
  @Column(name = "EXPAND_FIELD1")
  public String expandField1;
  @Column(name = "EXPAND_FIELD2")
  public String expandField2;
  @Column(name = "EXPAND_FIELD3")
  public String expandField3;
  @Column(name = "EXPAND_FIELD4")
  public String expandField4;
  @Column(name = "EXPAND_FIELD5")
  public String expandField5;
}
