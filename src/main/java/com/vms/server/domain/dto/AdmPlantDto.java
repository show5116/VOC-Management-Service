package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdmPlantDto {
    private String plant;
    private String description;
    private int numberOfShift;
    private String shift1Start;
    private String shift2Start;
    private String shift3Start;
    private String shift4Start;
    private int stdDaysPerWeek;
    private int stdHoursPerDay;
    private String activePlant;
    private String regTime;
    private String regUser;
    private String dml;
}
