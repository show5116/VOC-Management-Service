package com.vms.server.admin.repository.querydsl.impl;

import com.vms.server.admin.repository.querydsl.AdminIsendmailQueryRepository;
import com.vms.server.domain.dto.IsendmailDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.vms.server.domain.entity.etc.QIsendmail.isendmail;

@Repository
@RequiredArgsConstructor
public class AdminIsendmailQueryRepositoryImpl implements AdminIsendmailQueryRepository {

    final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<IsendmailDto> getSystem() {
        List<String> systemList = jpaQueryFactory
                .selectDistinct(
                        isendmail.systemName
                )
                .from(isendmail)
                .orderBy(isendmail.systemName.asc())
                .fetch();

        List<IsendmailDto> result = new ArrayList<>();
        for (String system : systemList) {
            String description = system;
            String code = system;
            result.add(new IsendmailDto(description, code));
        }

        return result;
    }
}
