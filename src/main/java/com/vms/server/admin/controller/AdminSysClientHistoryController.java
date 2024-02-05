package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminSysClientHistoryService;
import com.vms.server.domain.dto.SysClientHistoryDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminSysClientHistoryController {

    private final AdminSysClientHistoryService adminSysClientHistoryService;

    /**
     * 사용자 접속 로그 조회
     * @param sysClientHistoryDto(plant, startDate, endDate, login) 필수값 plant, startDate, endDate, login(LOGIN_TIME or LOGOUT_TIME)
     */
    @PostMapping("/admin/sys-client-history")
    public ResponseEntity<List<SysClientHistoryDto>> getSysClientHistory(@RequestBody SysClientHistoryDto sysClientHistoryDto) {
        List<SysClientHistoryDto> sysClientHistory = adminSysClientHistoryService.getAdminSysClientHistory(sysClientHistoryDto);
        return ResponseEntity.ok(sysClientHistory);
    }

    //TODO:SUPER_USER일 경우 삭제(SYS_CLIENT_HISTORY와 SYS_CLIENT)

    /**
     * 사용자 접속 로그 엑셀 다운로드
     */
    @PostMapping("/admin/sys-client-history/download-excel")
    public void downloadExcel(HttpServletResponse response, @RequestBody SysClientHistoryDto sysClientHistoryDto) throws IOException {
        List<SysClientHistoryDto> sysClientHistory = adminSysClientHistoryService.getAdminSysClientHistory(sysClientHistoryDto);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("구분");
        headerRow.createCell(1).setCellValue("컴퓨터명");
        headerRow.createCell(2).setCellValue("사용자명");
        headerRow.createCell(3).setCellValue("상태");
        headerRow.createCell(4).setCellValue("로그인 시간");
        headerRow.createCell(5).setCellValue("로그아웃 시간");
        headerRow.createCell(6).setCellValue("사용시간");
        headerRow.createCell(7).setCellValue("프로그램 버전");
        headerRow.createCell(8).setCellValue("접속IP");
        headerRow.createCell(9).setCellValue("실패 횟수");
        headerRow.getCell(0).setCellStyle(cellStyle);
        headerRow.getCell(1).setCellStyle(cellStyle);
        headerRow.getCell(2).setCellStyle(cellStyle);
        headerRow.getCell(3).setCellStyle(cellStyle);
        headerRow.getCell(4).setCellStyle(cellStyle);
        headerRow.getCell(5).setCellStyle(cellStyle);
        headerRow.getCell(6).setCellStyle(cellStyle);
        headerRow.getCell(7).setCellStyle(cellStyle);
        headerRow.getCell(8).setCellStyle(cellStyle);
        headerRow.getCell(9).setCellStyle(cellStyle);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);

        for (int i = 0; i < sysClientHistory.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(sysClientHistory.get(i).getUseProgram());
            dataRow.createCell(1).setCellValue(sysClientHistory.get(i).getComputerName());
            dataRow.createCell(2).setCellValue(sysClientHistory.get(i).getWorkingUser());
            dataRow.createCell(3).setCellValue(sysClientHistory.get(i).getStatus());
            dataRow.createCell(4).setCellValue(sysClientHistory.get(i).getLoginTime());
            dataRow.createCell(5).setCellValue(sysClientHistory.get(i).getLogoutTime());
            dataRow.createCell(6).setCellValue(sysClientHistory.get(i).getUseTime());
            dataRow.createCell(7).setCellValue(sysClientHistory.get(i).getProgramVersion());
            dataRow.createCell(8).setCellValue(sysClientHistory.get(i).getIpAddr());
            dataRow.createCell(9).setCellValue(sysClientHistory.get(i).getFailCount());
            dataRow.getCell(0).setCellStyle(dataStyle);
            dataRow.getCell(1).setCellStyle(dataStyle);
            dataRow.getCell(2).setCellStyle(dataStyle);
            dataRow.getCell(3).setCellStyle(dataStyle);
            dataRow.getCell(4).setCellStyle(dataStyle);
            dataRow.getCell(5).setCellStyle(dataStyle);
            dataRow.getCell(6).setCellStyle(dataStyle);
            dataRow.getCell(7).setCellStyle(dataStyle);
            dataRow.getCell(8).setCellStyle(dataStyle);
            dataRow.getCell(9).setCellStyle(dataStyle);
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
        }

        response.setContentType("ms-vnd/excel");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
