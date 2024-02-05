package com.vms.server.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsRfaAnalysisVo {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String seq;
    private String reqDate;
    private String faProcess;
    private String faQty;
    private String faReq;
    private String subTotal;
    private String compDate;
    private String closedFlag;
    private String fileSeq;
}
