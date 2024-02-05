package com.vms.server.qms.repository.dao;

public interface QMSCommonDao {

    int getQmsAttachedFileSeq(String plant, String systemName, String sysMType, String sysSType, String qmsNumber, String revisionNo);
    String getQmsNumber(String systemName);


}
