package com.vms.server.domain.entity.etc;

import javax.persistence.*;

import com.vms.server.domain.entity.etc.id.IsendmailAttachFileId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(IsendmailAttachFileId.class)
@Table(name = "ISENDMAIL_ATTACH_FILE")
public class IsendmailAttachFile {
  @Id
  @Column(name = "SEQ")
  public String seq;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "FILE_SEQ")
  public String fileSeq;
  @Column(name = "REAL_FILE_ID")
  public String realFileId;
  @Column(name = "ORG_FILE_ID")
  public String orgFileId;
  @Column(name = "FILE_PATH")
  public String filePath;
  @Column(name = "CREATE_TIME")
  public String createTime;
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
}
