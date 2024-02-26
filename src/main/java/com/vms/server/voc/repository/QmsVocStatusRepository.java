package com.vms.server.voc.repository;

import com.vms.server.domain.entity.qms.QmsVocStatus;
import com.vms.server.domain.entity.qms.id.QmsVocStatusId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface QmsVocStatusRepository extends Repository<QmsVocStatus, QmsVocStatusId>, QmsVocStatusRepositoryCustom {
    QmsVocStatus saveAndFlush(QmsVocStatus entity);
    List<QmsVocStatus> findAll();

    @Query("select max(voc.id.qmsNumber) " +
            " from QmsVocStatus voc ")
    String findMaxVocId();
}
