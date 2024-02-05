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
  public String plant;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "NUMBER_OF_SHIFT")
  public String numberOfShift;
  @Column(name = "SHIFT_1_START")
  public String shift1Start;
  @Column(name = "SHIFT_2_START")
  public String shift2Start;
  @Column(name = "SHIFT_3_START")
  public String shift3Start;
  @Column(name = "SHIFT_4_START")
  public String shift4Start;
  @Column(name = "STD_DAYS_PER_WEEK")
  public String stdDaysPerWeek;
  @Column(name = "STD_HOURS_PER_DAY")
  public String stdHoursPerDay;
  @Column(name = "ACTIVE_PLANT")
  public String activePlant;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
}
