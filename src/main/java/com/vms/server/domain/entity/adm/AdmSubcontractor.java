package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmSubcontractorId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmSubcontractorId.class)
@Table(name = "ADM_SUBCONTRACTOR")
public class AdmSubcontractor {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SUBCTRT_CODE")
  public String subctrtCode;
  @Column(name = "SUBCTRT_DESC")
  public String subctrtDesc;
  @Column(name = "ADDRESS_KOR")
  public String addressKor;
  @Column(name = "ADDRESS_ENG")
  public String addressEng;
  @Column(name = "ADDRESS_PLT1")
  public String addressPlt1;
  @Column(name = "ADDRESS_PLT2")
  public String addressPlt2;
  @Column(name = "ADDRESS_PLT3")
  public String addressPlt3;
  @Column(name = "ADDRESS_PLT4")
  public String addressPlt4;
  @Column(name = "ADDRESS_PLT5")
  public String addressPlt5;
  @Column(name = "SSN_SUBC")
  public String ssnSubc;
  @Column(name = "MAIL_NUM")
  public String mailNum;
  @Column(name = "TEL_NO")
  public String telNo;
  @Column(name = "FAX_NO")
  public String faxNo;
  @Column(name = "SUBC_NAME")
  public String subcName;
  @Column(name = "SUBC_E_MAIL")
  public String subcEMail;
  @Column(name = "IS_ABROAD")
  public String isAbroad;
  @Column(name = "USSAGE_YN")
  public String ussageYn;
  @Column(name = "ISFAB")
  public String isfab;
  @Column(name = "ISPRB")
  public String isprb;
  @Column(name = "ISPKG")
  public String ispkg;
  @Column(name = "ISFT")
  public String isft;
  @Column(name = "ISFTPK")
  public String isftpk;
  @Column(name = "ISWLP")
  public String iswlp;
  @Column(name = "ISWFT")
  public String iswft;
  @Column(name = "ISDPS")
  public String isdps;
  @Column(name = "REG_DATETIME")
  public String regDatetime;
  @Column(name = "REMARKS")
  public String remarks;
  @Column(name = "ISCOF")
  public String iscof;



}
