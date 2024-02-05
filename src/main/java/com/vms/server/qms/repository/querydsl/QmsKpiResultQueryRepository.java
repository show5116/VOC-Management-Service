package com.vms.server.qms.repository.querydsl;

import java.util.List;

public interface QmsKpiResultQueryRepository {

    List<String> getDocNumber(String plant, String titleSearch);

}
