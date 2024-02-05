package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMatItemId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMatItemId.class)
@Table(name = "QMS_MAT_ITEM")
public class QmsMatItem {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Id
  @Column(name = "MAT_CODE")
  public String matCode;
  @Id
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "TRACKING_NO")
  public String trackingNo;
  @Column(name = "PITCH")
  public String pitch;
  @Column(name = "USE_FLAG")
  public String useFlag;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "SUBC_CODE")
  public String subcCode;
}
