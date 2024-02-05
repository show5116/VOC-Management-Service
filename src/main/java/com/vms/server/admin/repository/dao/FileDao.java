package com.vms.server.admin.repository.dao;

import com.vms.server.domain.vo.QmsRfaAnalysisVo;
import com.vms.server.domain.vo.QmsRfaStatusVo;

import java.util.List;

public interface FileDao {
    QmsRfaStatusVo getRafStatusInfo(String plant, String facility, String qmsNo, String vender);

    List<QmsRfaAnalysisVo> getAnalySeq(String plant, String facility, String qmsNo, List<String> seq);

}
