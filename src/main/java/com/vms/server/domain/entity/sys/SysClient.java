package com.vms.server.domain.entity.sys;

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
@IdClass(SysClientId.class)
@Table(name = "SYS_CLIENT")
public class SysClient {
  @Id
  @Column(name = "COMPUTER_NAME")
  public String computerName;
  @Id
  @Column(name = "USE_PROGRAM")
  public String useProgram;
  @Column(name = "PROGRAM_VERSION")
  public String programVersion;
  @Column(name = "CLIENT_ID")
  public String clientId;
  @Column(name = "IN_PLANT")
  public String inPlant;
  @Id
  @Column(name = "LOGIN_TIME")
  public String loginTime;
  @Column(name = "LOGOUT_TIME")
  public String logoutTime;
  @Column(name = "WORKING_USER")
  public String workingUser;
  @Column(name = "STATUS")
  public String status;
  @Column(name = "LANGUAGE_SET")
  public String languageSet;
  @Column(name = "LAST_TRANS_CODE")
  public String lastTransCode;
  @Column(name = "LAST_TRANS_TIME")
  public String lastTransTime;
  @Column(name = "IP_ADDR")
  public String ipAddr;
  @Column(name = "FAIL_COUNT")
  public String failCount;
}
