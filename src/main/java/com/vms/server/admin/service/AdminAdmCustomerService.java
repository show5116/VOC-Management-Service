package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmCustomerDto;

import java.util.List;

public interface AdminAdmCustomerService {
    List<AdmCustomerDto> getAdmCustomer(AdmCustomerDto admCustomerDto);

    void insertAdmCustomer(AdmCustomerDto admCustomerDto);

    void deleteAdmCustomer(AdmCustomerDto admCustomerDto);
}
