package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAuditCarMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsAuditCarMasterId.class)
@Table(name = "QMS_AUDIT_CAR_MASTER")
public class QmsAuditCarMaster {
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
  @Column(name = "CAR_NUMBER")
  public String carNumber;
  @Column(name = "CAR_GRADE")
  public String carGrade;
  @Column(name = "CAR_CLASSIFICATION")
  public String carClassification;
  @Column(name = "CAR_DESCRIPTION")
  public String carDescription;
  @Column(name = "CAR_CORRECTIVE_ACTION")
  public String carCorrectiveAction;
  @Column(name = "CAR_OWNER")
  public String carOwner;
  @Column(name = "CAR_DUE_DATE")
  public String carDueDate;
  @Column(name = "CAR_COMPLETED_DATE")
  public String carCompletedDate;
  @Column(name = "STATUS")
  public String status;
}
