package com.vms.server.domain.entity.sys;

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
@Table(name = "SYS_SYSTEM_MESSAGE")
public class SysSystemMessage {
  @Id
  @Column(name = "MESSAGE_ID")
  public String messageId;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "MESSAGE_TYPE")
  public String messageType;
  @Column(name = "MESSAGE_CLASS")
  public String messageClass;
  @Column(name = "KOR_MSG_TITLE")
  public String korMsgTitle;
  @Column(name = "KOR_MSG_BODY")
  public String korMsgBody;
  @Column(name = "KOR_MSG_ACTION")
  public String korMsgAction;
  @Column(name = "ENG_MSG_TITLE")
  public String engMsgTitle;
  @Column(name = "ENG_MSG_BODY")
  public String engMsgBody;
  @Column(name = "ENG_MSG_ACTION")
  public String engMsgAction;
  @Column(name = "EXP_MSG_TITLE")
  public String expMsgTitle;
  @Column(name = "EXP_MSG_BODY")
  public String expMsgBody;
  @Column(name = "EXP_MSG_ACTION")
  public String expMsgAction;
}
