package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysCalendarId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysCalendarId.class)
@Table(name = "SYS_CALENDAR")
public class SysCalendar {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "NATURAL_DATE")
  public String naturalDate;
  @Column(name = "DAY_SEQ")
  public String daySeq;
  @Column(name = "WORK_DAY")
  public String workDay;
  @Column(name = "PLAN_YEAR")
  public String planYear;
  @Column(name = "PLAN_QUARTER")
  public String planQuarter;
  @Column(name = "PLAN_MONTH")
  public String planMonth;
  @Column(name = "PLAN_WEEK")
  public String planWeek;
}
