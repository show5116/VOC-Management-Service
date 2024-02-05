package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRfaSubcMappingId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRfaSubcMappingId.class)
@Table(name = "QMS_RFA_SUBC_MAPPING")
public class QmsRfaSubcMapping {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "FA_SUBC")
  public String faSubc;
  @Id
  @Column(name = "FA_PROCESS")
  public String faProcess;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
