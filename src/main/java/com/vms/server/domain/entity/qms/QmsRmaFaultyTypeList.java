package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRmaFaultyTypeListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRmaFaultyTypeListId.class)
@Table(name = "QMS_RMA_FAULTY_TYPE_LIST")
public class QmsRmaFaultyTypeList {
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
  @Column(name = "FAULTY_TYPE_M")
  public String faultyTypeM;
  @Column(name = "FAULTY_QTY")
  public String faultyQty;
  @Column(name = "FAULTY_REMARK")
  public String faultyRemark;
  @Id
  @Column(name = "FAULTY_TYPE_S")
  public String faultyTypeS;
}
