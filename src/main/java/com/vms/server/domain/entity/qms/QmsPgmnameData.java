package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPgmnameDataId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPgmnameDataId.class)
@Table(name = "QMS_PGMNAME_DATA")
public class QmsPgmnameData {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "PROGRAM_ID")
  public String programId;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "TEMPERATURE")
  public String temperature;
  @Column(name = "MULTI")
  public String multi;
  @Column(name = "PASS")
  public String pass;
  @Column(name = "TESTER")
  public String tester;
  @Column(name = "PROBER_TYPE")
  public String proberType;
  @Column(name = "PROBER_VALUE")
  public String proberValue;
  @Column(name = "INTERNAL_REVISION")
  public String internalRevision;
  @Column(name = "EXTERNAL_REVISION")
  public String externalRevision;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
}
