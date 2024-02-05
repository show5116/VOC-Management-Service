package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysFileLogId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysFileLogId.class)
@Table(name = "SYS_FILE_LOG")
public class SysFileLog {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Id
  @Column(name = "IP_ADDR")
  public String ipAddr;
  @Id
  @Column(name = "FILE_NAME")
  public String fileName;
  @Column(name = "STATUS")
  public String status;
}
