package com.vms.server.voc.dto.request;

import com.vms.server.voc.domain.type.DateKind;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class VocRequest {
    private DateKind dateKind;
    private String startDate;
    private String endDate;
    private String system;
}
