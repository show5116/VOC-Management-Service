package com.vms.server.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 현재 시간을 "yyyyMMddHHmmss" 형식의 문자열로 반환합니다.
     * @return 현재 시간 문자열
     */
    public static String getCurrentTimeFormatted() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    /**
     * 현재 날짜를 "yyyyMMdd" 형식의 문자열로 반환합니다.
     * @return 현재 날짜 문자열
     */
    public static String getCurrentDateFormatted() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(formatter);
    }
}
