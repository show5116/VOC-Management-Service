package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmCustomerId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmCustomerId.class)
@Table(name = "ADM_CUSTOMER")
public class AdmCustomer {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "CUSTOMER")
  public String customer;
  @Column(name = "CUSTOMER_NAME")
  public String customerName;
  @Column(name = "CUSTOMER_ALIAS")
  public String customerAlias;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "ADDRESS")
  public String address;
  @Column(name = "BUSINESS_STATUS")
  public String businessStatus;
  @Column(name = "BUSINESS_ITEM")
  public String businessItem;
  @Column(name = "BUSINESS_REGNO")
  public String businessRegno;
  @Column(name = "REPRESENTATION")
  public String representation;
  @Column(name = "CONTACT_OWNER")
  public String contactOwner;
  @Column(name = "CONTACT_POINT")
  public String contactPoint;
  @Column(name = "CONSIGNEE")
  public String consignee;
  @Column(name = "VENDOR_CODE")
  public String vendorCode;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
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

  public AdmCustomer(String customerName, String customer) {
    this.customerName = customerName;
    this.customer = customer;
  }

}
