package com.vms.server.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmPlantVo {
    private String plant;
    private String description;
    private String numberOfShift;
    private String shift1Start;
    private String shift2Start;
    private String shift3Start;
    private String shift4Start;
    private String stdDaysPerWeek;
    private String stdHoursPerDay;
    private String activePlant;
    private String regTime;
    private String regUser;
}
