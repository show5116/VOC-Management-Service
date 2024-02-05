package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRfaManagerId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRfaManagerId.class)
@Table(name = "QMS_RFA_MANAGER")
public class QmsRfaManager {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "FA_SUBC")
  public String faSubc;
  @Id
  @Column(name = "MANAGER")
  public String manager;
  @Column(name = "EMAIL")
  public String email;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
