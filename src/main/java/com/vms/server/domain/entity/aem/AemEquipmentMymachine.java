package com.vms.server.domain.entity.aem;

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
@Table(name = "AEM_EQUIPMENT_MYMACHINE")
public class AemEquipmentMymachine {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "AREA")
  public String area;
  @Column(name = "UGROUP")
  public String ugroup;
  @Column(name = "MODELNAME")
  public String modelname;
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Column(name = "USERID")
  public String userid;
  @Column(name = "USERNAME")
  public String username;
  @Column(name = "GRADE")
  public String grade;
}
