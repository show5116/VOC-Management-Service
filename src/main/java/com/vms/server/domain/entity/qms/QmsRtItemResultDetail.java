package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRtItemResultDetailId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRtItemResultDetailId.class)
@Table(name = "QMS_RT_ITEM_RESULT_DETAIL")
public class QmsRtItemResultDetail {
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
  @Id
  @Column(name = "TEST_TYPE")
  public String testType;
  @Id
  @Column(name = "STRESS")
  public String stress;
  @Id
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Id
  @Column(name = "DURATION")
  public String duration;
  @Id
  @Column(name = "FAULTY_ITEM")
  public String faultyItem;
  @Column(name = "TOTAL_QTY")
  public String totalQty;
  @Column(name = "FAIL_QTY")
  public String failQty;
  @Column(name = "DE_RESULT")
  public String deResult;
  @Column(name = "DE_JUDGEMENT")
  public String deJudgement;
  @Column(name = "DE_PLAN")
  public String dePlan;
  @Column(name = "DE_MANAGER")
  public String deManager;
  @Column(name = "FINAL_FAIL_QTY")
  public String finalFailQty;
  @Column(name = "QE_JUDGEMENT")
  public String qeJudgement;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
