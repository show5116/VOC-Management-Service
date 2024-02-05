package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMatSubcId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMatSubcId.class)
@Table(name = "QMS_MAT_SUBC")
public class QmsMatSubc {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SUBC_CODE")
  public String subcCode;
  @Id
  @Column(name = "MANAGER")
  public String manager;
  @Column(name = "EMAIL")
  public String email;
  @Column(name = "MAIN_FLAG")
  public String mainFlag;
  @Column(name = "WORK")
  public String work;
  @Column(name = "USE_FLAG")
  public String useFlag;
}
