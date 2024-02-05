package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsLsDetailId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsLsDetailId.class)
@Table(name = "QMS_LS_DETAIL")
public class QmsLsDetail {
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
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "CHECK_LIST")
  public String checkList;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "KEY_WORD")
  public String keyWord;
  @Column(name = "FUNCTION_TYPE")
  public String functionType;

  public QmsLsDetail(String plant, String systemName, String qmsNumber, String revisionNo, String seq, String checkList, String remark, String keyWord, String functionType) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.seq = seq;
    this.checkList = checkList;
    this.remark = remark;
    this.keyWord = keyWord;
    this.functionType = functionType;
  }
}
