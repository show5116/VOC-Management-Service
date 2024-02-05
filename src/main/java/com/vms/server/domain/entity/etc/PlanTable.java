package com.vms.server.domain.entity.etc;

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
@Table(name = "PLAN_TABLE")
public class PlanTable {
  @Id
  @Column(name = "STATEMENT_ID")
  public String statementId;
  @Column(name = "PLAN_ID")
  public String planId;
  @Column(name = "TIMESTAMP")
  public java.sql.Date timestamp;
  @Column(name = "REMARKS")
  public String remarks;
  @Column(name = "OPERATION")
  public String operation;
  @Column(name = "OPTIONS")
  public String options;
  @Column(name = "OBJECT_NODE")
  public String objectNode;
  @Column(name = "OBJECT_OWNER")
  public String objectOwner;
  @Column(name = "OBJECT_NAME")
  public String objectName;
  @Column(name = "OBJECT_ALIAS")
  public String objectAlias;
  @Column(name = "OBJECT_INSTANCE")
  public String objectInstance;
  @Column(name = "OBJECT_TYPE")
  public String objectType;
  @Column(name = "OPTIMIZER")
  public String optimizer;
  @Column(name = "SEARCH_COLUMNS")
  public String searchColumns;
  @Column(name = "ID")
  public String id;
  @Column(name = "PARENT_ID")
  public String parentId;
  @Column(name = "DEPTH")
  public String depth;
  @Column(name = "POSITION")
  public String position;
  @Column(name = "COST")
  public String cost;
  @Column(name = "CARDINALITY")
  public String cardinality;
  @Column(name = "BYTES")
  public String bytes;
  @Column(name = "OTHER_TAG")
  public String otherTag;
  @Column(name = "PARTITION_START")
  public String partitionStart;
  @Column(name = "PARTITION_STOP")
  public String partitionStop;
  @Column(name = "PARTITION_ID")
  public String partitionId;
  @Column(name = "OTHER")
  public String other;
  @Column(name = "DISTRIBUTION")
  public String distribution;
  @Column(name = "CPU_COST")
  public String cpuCost;
  @Column(name = "IO_COST")
  public String ioCost;
  @Column(name = "TEMP_SPACE")
  public String tempSpace;
  @Column(name = "ACCESS_PREDICATES")
  public String accessPredicates;
  @Column(name = "FILTER_PREDICATES")
  public String filterPredicates;
  @Column(name = "PROJECTION")
  public String projection;
  @Column(name = "TIME")
  public String time;
  @Column(name = "QBLOCK_NAME")
  public String qblockName;
  @Column(name = "OTHER_XML")
  public String otherXml;
}
