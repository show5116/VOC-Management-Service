package com.vms.server.domain.entity.adm.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AdmMyJobId implements Serializable {

    @Column(name = "EMP_CODE")
    public String empCode;

    @Column(name = "DEPT_ID")
    public String deptId;
}
