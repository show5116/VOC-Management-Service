package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAbnMajorDataId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsAbnMajorDataId.class)
@Table(name = "QMS_ABN_MAJOR_DATA")
public class QmsAbnMajorData {
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
  @Column(name = "MAJOR_TYPE")
  public String majorType;
  @Column(name = "MAJOR_SBIN")
  public String majorSbin;
  @Column(name = "MAJOR_ITEM")
  public String majorItem;
  @Column(name = "MAJOR_FAULTY_RATE")
  public String majorFaultyRate;
  @Column(name = "MAJOR_NOTE")
  public String majorNote;
}
