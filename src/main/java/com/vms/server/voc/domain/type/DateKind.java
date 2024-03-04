package com.vms.server.voc.domain.type;

import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.vms.server.domain.entity.qms.QQmsVocStatus.qmsVocStatus;

@AllArgsConstructor
@Getter
public enum DateKind {
    ISSUE_DATE("issueDate", qmsVocStatus.issueDate,"요청일"),
    REQUIRED_RESPONSE_DATE("requiredResponseDate", qmsVocStatus.requiredResponseDate,"납기 요청일"),
    EXPECTED_COMPLETION_DATE("expectedCompletionDate", qmsVocStatus.updateDate,"완료 예정일"),
    CLOSED_DATE("closedDate", qmsVocStatus.closedDate,"완료일");

    private String value;
    private StringPath column;
    private String korean;
}
