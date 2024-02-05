package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsAttachFileId.class)
@Table(name = "QMS_ATTACH_FILE")
public class QmsAttachFile {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "SYSTEM_NAME_MTYPE")
  public String systemNameMtype;
  @Id
  @Column(name = "SYSTEM_NAME_STYPE")
  public String systemNameStype;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "CREATE_TIME")
  public String createTime;
  @Column(name = "FILE_TYPE")
  public String fileType;
  @Id
  @Column(name = "FILE_SEQ")
  public Integer fileSeq;
  @Column(name = "REAL_FILE_ID")
  public String realFileId;
  @Column(name = "ORG_FILE_ID")
  public String orgFileId;
  @Column(name = "FILE_REMARK")
  public String fileRemark;
  @Column(name = "FILE_PATH")
  public String filePath;
  @Column(name = "FILE_EXIST_YN")
  public String fileExistYn;
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

  public QmsAttachFile(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo, String createTime, String fileType, int fileSeq, String realFileId, String orgFileId, String fileRemark, String filePath, String fileExistYn, String expandField1, String expandField2, String expandField3, String expandField4, String expandField5) {
    this.plant = plant;
    this.systemName = systemName;
    this.systemNameMtype = systemNameMtype;
    this.systemNameStype = systemNameStype;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.createTime = createTime;
    this.fileType = fileType;
    this.fileSeq = fileSeq;
    this.realFileId = realFileId;
    this.orgFileId = orgFileId;
    this.fileRemark = fileRemark;
    this.filePath = filePath;
    this.fileExistYn = fileExistYn;
    this.expandField1 = expandField1;
    this.expandField2 = expandField2;
    this.expandField3 = expandField3;
    this.expandField4 = expandField4;
    this.expandField5 = expandField5;
  }
}
