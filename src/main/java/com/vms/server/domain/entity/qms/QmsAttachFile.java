package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import com.vms.server.config.jpa.BooleanToYNConverter;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "innerBuilder")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QMS_ATTACH_FILE")
public class QmsAttachFile {
  @EmbeddedId
  private QmsAttachFileId id;
  @CreatedDate
  @Column(name = "CREATE_TIME")
  private String createTime;
  @Column(name = "FILE_TYPE")
  private String fileType;
  @Column(name = "REAL_FILE_ID")
  private String realFileId;
  @Column(name = "ORG_FILE_ID")
  private String orgFileId;
  @Column(name = "FILE_REMARK")
  private String fileRemark;
  @Column(name = "FILE_PATH")
  private String filePath;
  @Convert(converter = BooleanToYNConverter.class)
  @Column(name = "FILE_EXIST_YN")
  private Boolean fileExistYn;
  @Column(name = "EXPAND_FIELD1")
  private String expandField1;
  @Column(name = "EXPAND_FIELD2")
  private String expandField2;
  @Column(name = "EXPAND_FIELD3")
  private String expandField3;
  @Column(name = "EXPAND_FIELD4")
  private String expandField4;
  @Column(name = "EXPAND_FIELD5")
  private String expandField5;

  @PrePersist
  public void prePersist() {
    String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    this.createTime = customLocalDateTimeFormat;
  }

  public static QmsAttachFileBuilder builder(QmsAttachFileId id) {
    return innerBuilder().id(id);
  }
}
