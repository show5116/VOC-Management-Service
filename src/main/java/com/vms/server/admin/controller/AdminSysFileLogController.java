package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmUserInfoService;
import com.vms.server.admin.service.AdminSysFileLogService;
import com.vms.server.domain.dto.AdmUserInfoDto;
import com.vms.server.domain.dto.SysFileLogDto;
import com.vms.server.domain.vo.SysFileLogVo;
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
public class AdminSysFileLogController {

    private final AdminSysFileLogService adminSysFileLogService;
    private final AdminAdmUserInfoService adminAdmUserInfoService;

    /**
     * 파일 다운로드 로그 조회
     * @param sysFileLogDto(plant, startDate, endDate) 필수값 plant, startDate, endDate 선택값 status, userId, fileName
     */
    @PostMapping("/admin/sys-file-log")
    public ResponseEntity<List<SysFileLogVo>> getSysFileLog(@RequestBody SysFileLogDto sysFileLogDto) {
        List<SysFileLogVo> sysFileLog = adminSysFileLogService.getSysFileLog(sysFileLogDto);
        return ResponseEntity.ok(sysFileLog);
    }

    /**
     * 파일 다운로드 로그 검색 조건 - 사용자 (ADM-USER-INFO의 ROLE_ID가 RE_QMS_ADMIN)
     * @param admUserInfoDto(plant) 필수값 plant
     */
    @PostMapping("/admin/sys-file-log/user")
    public ResponseEntity<List<AdmUserInfoDto>> getUserInfoSysFileLog(@RequestBody AdmUserInfoDto admUserInfoDto) {
        List<AdmUserInfoDto> userInfo = adminAdmUserInfoService.getUserInfoSysFileLog(admUserInfoDto);
        return ResponseEntity.ok(userInfo);
    }

    //TODO:삭제기능이 필요한가?

    /**
     * 파일 다운로드 로그 엑셀 다운로드
     */
    @PostMapping("/admin/sys-file-log/download-excel")
    public void downloadExcel(HttpServletResponse response, @RequestBody SysFileLogDto sysFileLogDto) throws IOException {
        List<SysFileLogVo> sysFileLog = adminSysFileLogService.getSysFileLog(sysFileLogDto);
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
        headerRow.createCell(0).setCellValue("사번");
        headerRow.createCell(1).setCellValue("사용자");
        headerRow.createCell(2).setCellValue("부서");
        headerRow.createCell(3).setCellValue("구분");
        headerRow.createCell(4).setCellValue("접속IP");
        headerRow.createCell(5).setCellValue("파일명");
        headerRow.createCell(6).setCellValue("처리 시간");
        headerRow.getCell(0).setCellStyle(cellStyle);
        headerRow.getCell(1).setCellStyle(cellStyle);
        headerRow.getCell(2).setCellStyle(cellStyle);
        headerRow.getCell(3).setCellStyle(cellStyle);
        headerRow.getCell(4).setCellStyle(cellStyle);
        headerRow.getCell(5).setCellStyle(cellStyle);
        headerRow.getCell(6).setCellStyle(cellStyle);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);

        for (int i = 0; i < sysFileLog.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(sysFileLog.get(i).getUserId());
            dataRow.createCell(1).setCellValue(sysFileLog.get(i).getUserName());
            dataRow.createCell(2).setCellValue(sysFileLog.get(i).getDeptName());
            dataRow.createCell(3).setCellValue(sysFileLog.get(i).getStatus());
            dataRow.createCell(4).setCellValue(sysFileLog.get(i).getIpAddr());
            dataRow.createCell(5).setCellValue(sysFileLog.get(i).getFileName());
            dataRow.createCell(6).setCellValue(sysFileLog.get(i).getTransTime());
            dataRow.getCell(0).setCellStyle(dataStyle);
            dataRow.getCell(1).setCellStyle(dataStyle);
            dataRow.getCell(2).setCellStyle(dataStyle);
            dataRow.getCell(3).setCellStyle(dataStyle);
            dataRow.getCell(4).setCellStyle(dataStyle);
            dataRow.getCell(5).setCellStyle(dataStyle);
            dataRow.getCell(6).setCellStyle(dataStyle);
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
        }

        response.setContentType("ms-vnd/excel");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
