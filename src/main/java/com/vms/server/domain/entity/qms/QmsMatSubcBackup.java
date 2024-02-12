package com.vms.server.domain.entity.qms;

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
@Table(name = "QMS_MAT_SUBC_BACKUP")
public class QmsMatSubcBackup {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Column(name = "SUBC_CODE")
  public String subcCode;
  @Column(name = "UNIT")
  public String unit;
  @Column(name = "MANAGER")
  public String manager;
  @Column(name = "EMAIL_TO")
  public String emailTo;
  @Column(name = "EMAIL_CC")
  public String emailCc;
  @Column(name = "USE_FLAG")
  public String useFlag;
}
