package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionItemDto {

    private String seq;
    private String actionItem;
    private String person;
    private String completedDate;
    private String remark;
}
