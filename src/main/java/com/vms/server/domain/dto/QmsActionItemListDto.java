package com.vms.server.domain.dto;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsActionItemListDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String seqNo;
    private String actionItem;
    private String actRevNo;
    private String userId;
    private String userName;
    private String completedDate;
    private String remark;
    private String completedFlag;

    public void changeDateFormat(){
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = originalFormat.parse(this.completedDate);
            String formattedDate = targetFormat.format(date);
            this.completedDate = formattedDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
