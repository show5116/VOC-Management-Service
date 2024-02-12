package com.vms.server.domain.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ComboBoxItemDto {
    private String displayValue;
    private String value;
    private String description;
    private String color;
}
