package com.vms.server.domain.entity.etc;

import com.vms.server.domain.entity.etc.id.IsendmailId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(IsendmailId.class)
@Table(name = "ISENDMAIL")
public class Isendmail {
  @Id
  @Column(name = "SEQ")
  public String seq;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Column(name = "FROM_EMAIL")
  public String fromEmail;
  @Column(name = "TO_EMAIL")
  public String toEmail;
  @Column(name = "SUBJECT")
  public String subject;
  @Column(name = "SEND_FLAG")
  public Integer sendFlag;
  @Column(name = "CREATE_TIME")
  public String createTime;
  @Column(name = "CREATE_USER")
  public String createUser;
  @Column(name = "SEND_TIME")
  public String sendTime;
  @Column(name = "ERROR_MSG")
  public String errorMsg;
  @Column(name = "FILE_ID")
  public String fileId;
  @Column(name = "MESSAGE")
  public String message;
  @Column(name = "TO_CC")
  public String toCc;
  @Column(name = "TO_BCC")
  public String toBcc;
  @Column(name = "IS_HTML")
  public String isHtml;
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

  public void changeSendFlag(int flag){
    this.sendFlag = flag;
  }
}
