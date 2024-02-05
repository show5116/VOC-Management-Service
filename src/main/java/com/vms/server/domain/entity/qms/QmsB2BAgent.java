package com.vms.server.domain.entity.qms;

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
@Table(name = "QMS_B2B_AGENT")
public class QmsB2BAgent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "LOG_NAME")
  public String logName;
  @Column(name = "LOG_LEVEL")
  public String logLevel;
  @Column(name = "FACILITY")
  public String facility;
  @Column(name = "SUBCONTRACT")
  public String subcontract;
  @Column(name = "B2B_DATETIME")
  public String b2BDatetime;
  @Column(name = "B2B_FILENAME")
  public String b2BFilename;
  @Column(name = "B2B_FLAG")
  public String b2BFlag;
  @Column(name = "B2B_MESSAGE")
  public String b2BMessage;
  @Column(name = "B2B_FULLPATH")
  public String b2BFullpath;
}
