package com.vms.server.domain.entity.etc;

import javax.persistence.*;

import com.vms.server.domain.entity.etc.id.EvaluationItemListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(EvaluationItemListId.class)
@Table(name = "EVALUATION_ITEM_LIST")
public class EvaluationItemList {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EVALUATION_CODE")
  public String evaluationCode;
  @Id
  @Column(name = "CHANGE_TYPE")
  public String changeType;
  @Id
  @Column(name = "CHANGE_ITEM")
  public String changeItem;
  @Id
  @Column(name = "CHANGE_LEVEL")
  public String changeLevel;
  @Column(name = "USE_FLAG")
  public String useFlag;
}
