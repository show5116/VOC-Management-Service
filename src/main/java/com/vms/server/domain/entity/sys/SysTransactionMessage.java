package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysTransactionMessageId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysTransactionMessageId.class)
@Table(name = "SYS_TRANSACTION_MESSAGE")
public class SysTransactionMessage {
  @Id
  @Column(name = "INDUSTRIAL_TYPE")
  public String industrialType;
  @Id
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Id
  @Column(name = "TRANSACTION")
  public String transaction;
  @Id
  @Column(name = "MESSAGE_ID")
  public String messageId;
  @Column(name = "MESSAGE_SEQ")
  public String messageSeq;
  @Column(name = "MESSAGE_NAME")
  public String messageName;
  @Column(name = "MESSAGE_ENAME")
  public String messageEname;
  @Column(name = "MESSAGE_DESC")
  public String messageDesc;
  @Column(name = "MUST_FLAG")
  public String mustFlag;
  @Column(name = "FORMAT_TYPE")
  public String formatType;
  @Column(name = "MULTI_VALUE_FLAG")
  public String multiValueFlag;
  @Column(name = "MAX_LENGTH")
  public String maxLength;
  @Column(name = "DEFAULT_VALUE")
  public String defaultValue;
  @Column(name = "UPPER_FLAG")
  public String upperFlag;
  @Column(name = "TRIM_FLAG")
  public String trimFlag;
  @Column(name = "STANDARD_FLAG")
  public String standardFlag;
  @Column(name = "ENABLE_FLAG")
  public String enableFlag;
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
