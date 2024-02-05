package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsDeviceInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsDeviceInfoId.class)
@Table(name = "QMS_DEVICE_INFO")
public class QmsDeviceInfo {
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
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "MVT")
  public String mvt;
  @Column(name = "DEVICE_OPTION")
  public String deviceOption;
  @Column(name = "APPLICATION_1")
  public String application1;
  @Column(name = "APPLICATION_2")
  public String application2;
  @Column(name = "DEVICE_REMARK")
  public String deviceRemark;
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "STATUS")
  public String status;
}
