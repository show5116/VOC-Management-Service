package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmCustomerRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmCustomerQueryRepository;
import com.vms.server.admin.service.AdminAdmCustomerService;
import com.vms.server.domain.dto.AdmCustomerDto;
import com.vms.server.domain.entity.adm.AdmCustomer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmCustomerServiceImpl implements AdminAdmCustomerService {

    private final AdminAdmCustomerQueryRepository adminAdmCustomerQueryRepository;
    private final AdminAdmCustomerRepository adminAdmCustomerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmCustomerDto> getAdmCustomer(AdmCustomerDto admCustomerDto) {
        String plant = admCustomerDto.getPlant();
        String customerCode = admCustomerDto.getCustomer();
        String customerName = admCustomerDto.getCustomerName();
        List<AdmCustomerDto> result = new ArrayList<>();
        List<AdmCustomer> customerList = adminAdmCustomerQueryRepository.getAdmCustomer(plant, customerCode, customerName);
        customerList.forEach(admCustomer -> result.add(modelMapper.map(admCustomer, AdmCustomerDto.class)));

        return result;
    }

    @Override
    public void insertAdmCustomer(AdmCustomerDto admCustomerDto) {
        if (admCustomerDto != null) {
            AdmCustomer admCustomer = modelMapper.map(admCustomerDto, AdmCustomer.class);
            adminAdmCustomerRepository.save(admCustomer);
        }
    }

    @Override
    public void deleteAdmCustomer(AdmCustomerDto admCustomerDto) {
        if (admCustomerDto != null) {
            AdmCustomer admCustomer = modelMapper.map(admCustomerDto, AdmCustomer.class);
            adminAdmCustomerRepository.delete(admCustomer);
        }
    }
}
