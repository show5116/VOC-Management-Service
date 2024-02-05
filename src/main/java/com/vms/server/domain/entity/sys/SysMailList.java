package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysMailListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysMailListId.class)
@Table(name = "SYS_MAIL_LIST")
public class SysMailList {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "MAIL_CODE")
  public String mailCode;
  @Column(name = "MAIL_TYPE")
  public String mailType;
  @Column(name = "MAIL_TITLE")
  public String mailTitle;
  @Column(name = "MESSAGE_KOR")
  public String messageKor;
  @Column(name = "MESSAGE_ENG")
  public String messageEng;
  @Column(name = "APPROVAL_FLAG")
  public String approvalFlag;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "SEND_ENG_FLAG")
  public String sendEngFlag;
}
