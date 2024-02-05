package com.vms.server.domain.entity.etc;

import javax.persistence.*;

import com.vms.server.domain.entity.etc.id.SqErpIfAutoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SqErpIfAutoId.class)
@Table(name = "SQ_ERP_IF_AUTO")
public class SqErpIfAuto {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "TBL_NM")
  public String tblNm;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "TRANS_TYPE")
  public String transType;
  @Column(name = "ROW_CNT")
  public String rowCnt;
  @Column(name = "MOVE_FLAG")
  public String moveFlag;
  @Column(name = "SUCESS_FLAG")
  public String sucessFlag;
  @Column(name = "MSG")
  public String msg;
  @Column(name = "END_TRANS_TIME")
  public String endTransTime;
}
