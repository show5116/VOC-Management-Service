package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsKpiResultDetailStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsKpiResultDetailStatusId.class)
@Table(name = "QMS_KPI_RESULT_DETAIL_STATUS")
public class QmsKpiResultDetailStatus {
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
  @Column(name = "LST_QMS_NUMBER")
  public String lstQmsNumber;
  @Id
  @Column(name = "LST_REVISION_NO")
  public String lstRevisionNo;
  @Column(name = "TARGET")
  public String target;
  @Column(name = "RESULT_1M")
  public String result1M;
  @Column(name = "RESULT_2M")
  public String result2M;
  @Column(name = "RESULT_3M")
  public String result3M;
  @Column(name = "RESULT_4M")
  public String result4M;
  @Column(name = "RESULT_5M")
  public String result5M;
  @Column(name = "RESULT_6M")
  public String result6M;
  @Column(name = "RESULT_7M")
  public String result7M;
  @Column(name = "RESULT_8M")
  public String result8M;
  @Column(name = "RESULT_9M")
  public String result9M;
  @Column(name = "RESULT_10M")
  public String result10M;
  @Column(name = "RESULT_11M")
  public String result11M;
  @Column(name = "RESULT_12M")
  public String result12M;
  @Column(name = "RESULT_1Q")
  public String result1Q;
  @Column(name = "RESULT_2Q")
  public String result2Q;
  @Column(name = "RESULT_3Q")
  public String result3Q;
  @Column(name = "RESULT_4Q")
  public String result4Q;
  @Column(name = "RESULT_1H")
  public String result1H;
  @Column(name = "RESULT_2H")
  public String result2H;
  @Column(name = "RESULT_1Y")
  public String result1Y;
}
