package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsTestDeviceInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsTestDeviceInfoId.class)
@Table(name = "QMS_TEST_DEVICE_INFO")
public class QmsTestDeviceInfo {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "DEVICE_REMARK")
  public String deviceRemark;
}
