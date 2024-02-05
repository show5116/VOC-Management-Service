package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPmTargetId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPmTargetId.class)
@Table(name = "QMS_PM_TARGET")
public class QmsPmTarget {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "TARGET_YEAR")
  public String targetYear;
  @Column(name = "TARGET_VALUE")
  public String targetValue;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
