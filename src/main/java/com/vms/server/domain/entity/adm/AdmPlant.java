package com.vms.server.domain.entity.adm;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ADM_PLANT")
public class AdmPlant {
  @Id
  @Column(name = "PLANT")
  private String plant;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "NUMBER_OF_SHIFT")
  private String numberOfShift;
  @Column(name = "SHIFT_1_START")
  private String shift1Start;
  @Column(name = "SHIFT_2_START")
  private String shift2Start;
  @Column(name = "SHIFT_3_START")
  private String shift3Start;
  @Column(name = "SHIFT_4_START")
  private String shift4Start;
  @Column(name = "STD_DAYS_PER_WEEK")
  private String stdDaysPerWeek;
  @Column(name = "STD_HOURS_PER_DAY")
  private String stdHoursPerDay;
  @Column(name = "ACTIVE_PLANT")
  private String activePlant;
  @Column(name = "REG_TIME")
  private String regTime;
  @Column(name = "REG_USER")
  private String regUser;
}
