package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmCustomerService;
import com.vms.server.domain.dto.AdmCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminAdmCustomerController {

    private final AdminAdmCustomerService adminAdmCustomerService;

    @PostMapping("/admin/adm-customer")
    public ResponseEntity<List<AdmCustomerDto>> getAdmCustomer(@RequestBody AdmCustomerDto admCustomerDto) {


        String dml = admCustomerDto.getDml();
        switch (dml) {
            /**
             * 고객사 등록&수정
             * @param admCustomerDto(plant, customer) 필수값 plant, customer
             */
            case "i":
                adminAdmCustomerService.insertAdmCustomer(admCustomerDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 고객사 삭제
             * @param admCustomerDto(plant, customer) 필수값 plant, customer
             */
            case "d":
                adminAdmCustomerService.deleteAdmCustomer(admCustomerDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 고객사 조회
             * @param admCustomerDto(plant, customerName, customer) 필수값 plant, 선택값 customer or customerName
             */
            default:
                List<AdmCustomerDto> customer = adminAdmCustomerService.getAdmCustomer(admCustomerDto);
                return ResponseEntity.ok(customer);
        }

    }
}
