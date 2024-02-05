package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.entity.adm.AdmCustomer;

import java.util.List;

public interface AdminAdmCustomerQueryRepository {

    List<AdmCustomer> getCustomerList(String plant);

    List<AdmCustomer> getAdmCustomer(String plant, String customerCode, String customerName);
}
