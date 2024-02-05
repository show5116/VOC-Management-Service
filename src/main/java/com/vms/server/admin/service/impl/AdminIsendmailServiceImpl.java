package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminIsendMailDao;
import com.vms.server.admin.repository.querydsl.AdminIsendmailQueryRepository;
import com.vms.server.admin.service.AdminIsendmailService;
import com.vms.server.domain.dto.IsendmailDto;
import com.vms.server.domain.vo.IsendmailVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminIsendmailServiceImpl implements AdminIsendmailService {

    private final AdminIsendmailQueryRepository adminIsendmailQueryRepository;
    private final AdminIsendMailDao adminIsendMailDao;
    private final ModelMapper modelMapper;

    @Override
    public List<IsendmailVo> getIsendmail(IsendmailDto isendmailDto) {
        List<IsendmailDto> isendmailList = adminIsendMailDao.getIsendmail(isendmailDto);
        return isendmailList.stream().map(isendmail -> new IsendmailVo(
                isendmail.getCreateUserR(),
                Optional.ofNullable(isendmail.getCreateUser()).orElse(Collections.emptyList()).stream().collect(Collectors.joining(", ")),
                isendmail.getDeptId(),
                Optional.ofNullable(isendmail.getSystemName()).orElse(Collections.emptyList()).stream().collect(Collectors.joining(", ")),
                isendmail.getSubject(),
                isendmail.getFromEmail(),
                isendmail.getToEmail(),
                isendmail.getToCc(),
                Optional.ofNullable(isendmail.getSendFlag()).orElse(Collections.emptyList()).stream().collect(Collectors.joining(", ")),
                isendmail.getCreateTime(),
                isendmail.getSendTime(),
                isendmail.getMessage()
        )).collect(Collectors.toList());
    }

    @Override
    public List<IsendmailDto> getSystem() {
        return adminIsendmailQueryRepository.getSystem();
    }
}
