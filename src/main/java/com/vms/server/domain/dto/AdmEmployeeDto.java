package com.vms.server.domain.dto;

import com.vms.server.domain.entity.adm.AdmEmployee;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmEmployeeDto {
    private String userId;
    private String password;
    private String empName;
    private String empEnName;
    private String empEmailName;
    private String licNo;
    private String solarYn;
    private String birth;
    private String hireDate;
    private String outDate;
    private String empCode;
    private String posId;
    private Integer empLevel;
    private Integer secLevel;
    private String empStatus;
    private String mobile;
    private String phone;
    private String fax;
    private String email;
    private String zipCode;
    private String homeAddr;
    private String homeAddr2;
    private String empCharge;
    private String homeTel;
    private String cdZipcode;
    private String ifdata;

    public AdmEmployeeDto(AdmEmployee admEmployee) {
        this.userId = admEmployee.getUserId();
        this.password = admEmployee.getPassword();
        this.empName = admEmployee.getEmpName();
        this.empEnName = admEmployee.getEmpEnName();
        this.empEmailName = admEmployee.getEmpEmailName();
        this.solarYn = admEmployee.getSolarYn();
        this.birth = admEmployee.getBirth();
        this.hireDate = admEmployee.getHireDate();
        this.outDate = admEmployee.getOutDate();
        this.posId = admEmployee.getPosId();
        this.empLevel = admEmployee.getEmpLevel();
        this.secLevel = admEmployee.getSecLevel();
        this.empStatus = admEmployee.getEmpStatus();
        this.mobile = admEmployee.getMobile();
        this.phone = admEmployee.getPhone();
        this.fax = admEmployee.getFax();
        this.email = admEmployee.getEmail();
        this.zipCode = admEmployee.getZipCode();
        this.homeAddr = admEmployee.getHomeAddr();
        this.homeAddr2 = admEmployee.getHomeAddr2();
        this.empCharge = admEmployee.getEmpCharge();
        this.homeTel = admEmployee.getHomeTel();
        this.cdZipcode = admEmployee.getCdZipcode();
        this.ifdata = admEmployee.getIfdata();
    }
}
