package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsDocumentDownloadListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsDocumentDownloadListId.class)
@Table(name = "QMS_DOCUMENT_DOWNLOAD_LIST")
public class QmsDocumentDownloadList {
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
  @Column(name = "REQUEST_USER")
  public String requestUser;
  @Id
  @Column(name = "REQUEST_DATE")
  public String requestDate;
  @Column(name = "REQUEST_REMARK")
  public String requestRemark;
  @Column(name = "APPROVAL_USER")
  public String approvalUser;
  @Column(name = "APPROVAL_DATE")
  public String approvalDate;
  @Column(name = "APPROVAL_FLAG")
  public String approvalFlag;
  @Column(name = "APPROVAL_REMARK")
  public String approvalRemark;
  @Column(name = "DOWNLOAD_FLAG")
  public String downloadFlag;
}
