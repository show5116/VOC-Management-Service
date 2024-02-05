package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.dto.AdmEmployeeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ADM_EMPLOYEE")
public class AdmEmployee {
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "PASSWORD")
  public String password;
  @Column(name = "EMP_NAME")
  public String empName;
  @Column(name = "EMP_EN_NAME")
  public String empEnName;
  @Column(name = "EMP_EMAIL_NAME")
  public String empEmailName;
  @Column(name = "LIC_NO")
  public String licNo;
  @Column(name = "SOLAR_YN")
  public String solarYn;
  @Column(name = "BIRTH")
  public String birth;
  @Column(name = "HIRE_DATE")
  public String hireDate;
  @Column(name = "OUT_DATE")
  public String outDate;
  @Column(name = "EMP_CODE")
  public String empCode;
  @Column(name = "POS_ID")
  public String posId;
  @Column(name = "EMP_LEVEL")
  public Integer empLevel;
  @Column(name = "SEC_LEVEL")
  public Integer secLevel;
  @Column(name = "EMP_STATUS")
  public String empStatus;
  @Column(name = "MOBILE")
  public String mobile;
  @Column(name = "PHONE")
  public String phone;
  @Column(name = "FAX")
  public String fax;
  @Column(name = "EMAIL")
  public String email;
  @Column(name = "ZIP_CODE")
  public String zipCode;
  @Column(name = "HOME_ADDR")
  public String homeAddr;
  @Column(name = "HOME_ADDR2")
  public String homeAddr2;
  @Column(name = "EMP_CHARGE")
  public String empCharge;
  @Column(name = "HOME_TEL")
  public String homeTel;
  @Column(name = "CD_ZIPCODE")
  public String cdZipcode;
  @Column(name = "IFDATA")
  public String ifdata;

  public AdmEmployee(String userId, String password, String empName, String empEnName, String empEmailName, String licNo, String solarYn, String birth, String hireDate, String outDate, String empCode, String posId, int empLevel, int secLevel, String empStatus, String mobile, String phone, String fax, String email, String zipCode, String homeAddr, String homeAddr2, String empCharge, String homeTel, String cdZipcode, String ifdata) {
    this.userId = userId;
    this.password = password;
    this.empName = empName;
    this.empEnName = empEnName;
    this.empEmailName = empEmailName;
    this.licNo = licNo;
    this.solarYn = solarYn;
    this.birth = birth;
    this.hireDate = hireDate;
    this.outDate = outDate;
    this.empCode = empCode;
    this.posId = posId;
    this.empLevel = empLevel;
    this.secLevel = secLevel;
    this.empStatus = empStatus;
    this.mobile = mobile;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
    this.zipCode = zipCode;
    this.homeAddr = homeAddr;
    this.homeAddr2 = homeAddr2;
    this.empCharge = empCharge;
    this.homeTel = homeTel;
    this.cdZipcode = cdZipcode;
    this.ifdata = ifdata;
  }

  public static AdmEmployeeDto getBeforeUser(AdmEmployee admEmployee){

    return new AdmEmployeeDto(admEmployee);
  }


}
