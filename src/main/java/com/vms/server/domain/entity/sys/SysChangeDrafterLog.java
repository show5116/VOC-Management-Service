package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysChangeDrafterLogId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysChangeDrafterLogId.class)
@Table(name = "SYS_CHANGE_DRAFTER_LOG")
public class SysChangeDrafterLog {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "TITLE")
  public String title;
  @Column(name = "OLD_DRAFTER")
  public String oldDrafter;
  @Column(name = "NEW_DRAFTER")
  public String newDrafter;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "REMARK")
  public String remark;

  public SysChangeDrafterLog(String plant, String systemName, String qmsNumber, String revisionNo, String transTime, String title, String oldDrafter, String newDrafter, String regUser, String remark) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.transTime = transTime;
    this.title = title;
    this.oldDrafter = oldDrafter;
    this.newDrafter = newDrafter;
    this.regUser = regUser;
    this.remark = remark;
  }
}
