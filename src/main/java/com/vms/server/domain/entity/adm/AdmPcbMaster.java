package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmPcbMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmPcbMasterId.class)
@Table(name = "ADM_PCB_MASTER")
public class AdmPcbMaster {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "PCB_ID")
  public String pcbId;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "TESTER")
  public String tester;
  @Column(name = "DUT_CNT")
  public String dutCnt;
  @Column(name = "PART_NAME")
  public String partName;
  @Column(name = "SEQ_NO")
  public String seqNo;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
}
