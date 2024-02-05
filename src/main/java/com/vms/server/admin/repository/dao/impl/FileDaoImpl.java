package com.vms.server.admin.repository.dao.impl;

import com.vms.server.admin.repository.dao.FileDao;
import com.vms.server.common.CustomBeanPropertyRowMapper;
import com.vms.server.domain.vo.QmsRfaAnalysisVo;
import com.vms.server.domain.vo.QmsRfaStatusVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDaoImpl implements FileDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public QmsRfaStatusVo getRafStatusInfo(String plant, String facility, String qmsNo, String vender) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT PLANT\n");
        sb.append("       , SYSTEM_NAME\n");
        sb.append("       , QMS_NUMBER\n");
        sb.append("       , REVISION_NO\n");
        sb.append("       , CURRENT_STEP\n");
        sb.append("       , DEVICE\n");
        sb.append("       , FA_SUBC\n");
        sb.append("       , FA_MANAGER\n");
        sb.append("       , REMARK\n");
        sb.append("       , CLOSED_FLAG\n");
        sb.append("       , CLOSED_DATE\n");
        sb.append("       , CLOSED_USER\n");
        sb.append("       , GET_USERID_DESC_ONLY(PLANT, REG_USER) || '<' || GET_USER_EMAIL(REG_USER) || '>' AS TO_EMAIL\n");
// 2023.03.29 KYT RFA 문서 수신시 메일 양식 변경
        sb.append("       , (SELECT LISTAGG(EMAIL, ',') WITHIN GROUP(ORDER BY EMAIL)\n");
        sb.append("                 FROM (\n");
        sb.append("                       SELECT GET_USERID_DESC_ONLY(PLANT, ITEM_CODE) || '<' || GET_USER_EMAIL(ITEM_CODE) || '>' AS EMAIL\n");
        sb.append("                         FROM QMS_SELECTED_LIST LST\n");
        sb.append("                        WHERE PLANT =:plant \n");
        sb.append("                          AND SYSTEM_NAME = :facility \n");
        sb.append("                          AND QMS_NUMBER = :qmsNo \n");
        sb.append("                          AND REVISION_NO = (SELECT MAX(REVISION_NO)\n");
        sb.append("                                               FROM QMS_RFA_STATUS\n");
        sb.append("                                              WHERE PLANT = LST.PLANT\n");
        sb.append("                                                AND SYSTEM_NAME = LST.SYSTEM_NAME\n");
        sb.append("                                                AND QMS_NUMBER = LST.QMS_NUMBER)\n");
        sb.append("                          AND ITEM_TYPE = 'SHARE'\n");
        sb.append("                        UNION\n");
        sb.append("                        SELECT MANAGER || '<' || EMAIL || '>' AS EMAIL\n");
        sb.append("                          FROM QMS_RFA_MANAGER\n");
        sb.append("                         WHERE 1 = 1\n");
        sb.append("                           AND PLANT = :plant \n");
        sb.append("                           AND FA_SUBC = :vender \n");
        sb.append("                      )) AS CC_EMAIL\n");
// 2023.03.29 KYT
        sb.append("       , GET_SYSCODE_SP1_ONLY(PLANT, 'QMS_SYSTEM_NAME', SYSTEM_NAME) AS SYSTEM_GROUP\n");
        sb.append("       , GET_SYSCODE_DESC_ONLY(PLANT, 'QMS_SYSTEM_NAME', SYSTEM_NAME) AS SYSTEM_DESC\n");
        sb.append("  FROM QMS_RFA_STATUS A\n");
        sb.append(" WHERE 1 = 1\n");
        sb.append("   AND PLANT = :plant \n");
        sb.append("   AND SYSTEM_NAME = :facility \n");
        sb.append("   AND QMS_NUMBER =  :qmsNo \n");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("facility", facility);
        parameters.addValue("qmsNo", qmsNo);
        parameters.addValue("vender", vender);

        QmsRfaStatusVo result = jdbcTemplate.queryForObject(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(QmsRfaStatusVo.class)
        );
        return result;
    }

    @Override
    public List<QmsRfaAnalysisVo> getAnalySeq(String plant, String facility, String qmsNo, List<String> seq) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT LISTAGG(FILE_SEQ, ',') WITHIN GROUP (ORDER BY REVISION_NO) AS FILE_SEQ\n");
        sb.append("  FROM (\n");
        sb.append("        SELECT DISTINCT\n");
        sb.append("               TO_NUMBER(REVISION_NO) || '차' AS FILE_SEQ\n");
        sb.append("               , REVISION_NO\n");
        sb.append("          FROM QMS_RFA_ANALYSIS\n");
        sb.append("         WHERE PLANT = :plant \n");
        sb.append("           AND SYSTEM_NAME = :facility \n");
        sb.append("           AND QMS_NUMBER = :qmsNo \n");
        sb.append("           AND SEQ IN ( :seq )\n");
        sb.append("       )\n");


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("plant", plant);
        parameters.addValue("facility", facility);
        parameters.addValue("qmsNo", qmsNo);
        parameters.addValue("seq", seq);

        List<QmsRfaAnalysisVo> result = jdbcTemplate.query(
                sb.toString(),
                parameters,
                new CustomBeanPropertyRowMapper<>(QmsRfaAnalysisVo.class)
        );
        return result;
    }
}
