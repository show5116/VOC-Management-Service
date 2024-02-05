package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsOrdExportId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsOrdExportId.class)
@Table(name = "QMS_ORD_EXPORT")
public class QmsOrdExport {
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
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "REQ_CONTENT")
  public String reqContent;
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PURPOSE")
  public String purpose;
  @Column(name = "PRD_TYPE")
  public String prdType;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "TRACKING_NO")
  public String trackingNo;
  @Column(name = "SUBC_CODE")
  public String subcCode;
  @Column(name = "MAT_CODE")
  public String matCode;
  @Column(name = "EXPORT_DATE")
  public String exportDate;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
}
