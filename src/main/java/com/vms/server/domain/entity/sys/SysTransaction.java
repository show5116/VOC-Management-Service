package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysTransactionId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysTransactionId.class)
@Table(name = "SYS_TRANSACTION")
public class SysTransaction {
  @Id
  @Column(name = "INDUSTRIAL_TYPE")
  public String industrialType;
  @Id
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Id
  @Column(name = "TRANSACTION")
  public String transaction;
  @Column(name = "TRANSACTION_NAME")
  public String transactionName;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "WEBSERVICE_URL")
  public String webserviceUrl;
  @Column(name = "ENABLE_FLAG")
  public String enableFlag;
  @Column(name = "OPTIONAL_FLAG")
  public String optionalFlag;
  @Column(name = "STANDARD_FLAG")
  public String standardFlag;
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
