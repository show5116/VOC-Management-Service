package com.vms.server.voc.repository;

import com.vms.server.domain.entity.qms.QmsVocHistory;
import com.vms.server.domain.entity.qms.id.QmsVocHistoryId;
import org.springframework.data.repository.Repository;

public interface QmsVocHistoryRepository extends Repository<QmsVocHistory, QmsVocHistoryId> {
    QmsVocHistory save(QmsVocHistory entity);
}
