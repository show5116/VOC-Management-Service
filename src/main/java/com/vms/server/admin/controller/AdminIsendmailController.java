package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmUserInfoService;
import com.vms.server.admin.service.AdminIsendmailService;
import com.vms.server.domain.dto.AdmUserInfoDto;
import com.vms.server.domain.dto.IsendmailDto;
import com.vms.server.domain.vo.IsendmailVo;
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
public class AdminIsendmailController {

    private final AdminIsendmailService adminIsendmailService;
    private final AdminAdmUserInfoService adminAdmUserInfoService;

    /**
     * 메일 발송 로그 조회
     * @param isendmailDto(plant, startDate, endDate)
     * 필수값 plant, startDate, endDate
     * 선택값 systemName, createUser(user_id), sendFlag, fromEmail, toEmail, toCc, subject, message
     */
    @PostMapping("/admin/isendmail")
    public ResponseEntity<List<IsendmailVo>> getIsendmail(@RequestBody IsendmailDto isendmailDto) {
        List<IsendmailVo> isendmail = adminIsendmailService.getIsendmail(isendmailDto);
        return ResponseEntity.ok(isendmail);
    }

    /**
     * 메일 발송 로그에 등록된 시스템
     */
    @PostMapping("/admin/isendmail/system")
    public ResponseEntity<List<IsendmailDto>> getSystem() {
        List<IsendmailDto> system = adminIsendmailService.getSystem();
        return ResponseEntity.ok(system);
    }

    /**
     * 메일 발송 로그 조회 조건 - 사용자
     * @param admUserInfoDto
     * @return
     */
    @PostMapping("/admin/isendmail/user")
    public ResponseEntity<List<AdmUserInfoDto>> getUserInfo(@RequestBody AdmUserInfoDto admUserInfoDto) {
        List<AdmUserInfoDto> userInfo = adminAdmUserInfoService.getUserInfo(admUserInfoDto);
        return ResponseEntity.ok(userInfo);
    }

    /**
     * 메일 발송 로그 엑셀 다운로드
     */
    @PostMapping("/admin/isendmail/download-excel")
    public void downloadExcel(HttpServletResponse response , @RequestBody IsendmailDto isendmailDto) throws IOException {
        List<IsendmailVo> isendmail = adminIsendmailService.getIsendmail(isendmailDto);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        CellStyle yellowCellStyle = workbook.createCellStyle();
        yellowCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        yellowCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        yellowCellStyle.setBorderTop(BorderStyle.THIN);
        yellowCellStyle.setBorderBottom(BorderStyle.THIN);
        yellowCellStyle.setBorderLeft(BorderStyle.THIN);
        yellowCellStyle.setBorderRight(BorderStyle.THIN);
        yellowCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("사번");
        headerRow.createCell(1).setCellValue("사용자");
        headerRow.createCell(2).setCellValue("부서");
        headerRow.createCell(3).setCellValue("시스템");
        headerRow.createCell(4).setCellValue("제목");
        headerRow.createCell(5).setCellValue("보낸 사람");
        headerRow.createCell(6).setCellValue("받는 사람");
        headerRow.createCell(7).setCellValue("참조");
        headerRow.createCell(8).setCellValue("상태");
        headerRow.createCell(9).setCellValue("생성일자");
        headerRow.createCell(10).setCellValue("발송일자");
        headerRow.getCell(0).setCellStyle(yellowCellStyle);
        headerRow.getCell(1).setCellStyle(yellowCellStyle);
        headerRow.getCell(2).setCellStyle(yellowCellStyle);
        headerRow.getCell(3).setCellStyle(yellowCellStyle);
        headerRow.getCell(4).setCellStyle(yellowCellStyle);
        headerRow.getCell(5).setCellStyle(yellowCellStyle);
        headerRow.getCell(6).setCellStyle(yellowCellStyle);
        headerRow.getCell(7).setCellStyle(yellowCellStyle);
        headerRow.getCell(8).setCellStyle(yellowCellStyle);
        headerRow.getCell(9).setCellStyle(yellowCellStyle);
        headerRow.getCell(10).setCellStyle(yellowCellStyle);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);

        for (int i = 0; i < isendmail.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(isendmail.get(i).getCreateUserR());
            dataRow.createCell(1).setCellValue(isendmail.get(i).getCreateUser());
            dataRow.createCell(2).setCellValue(isendmail.get(i).getDeptId());
            dataRow.createCell(3).setCellValue(isendmail.get(i).getSystemName());
            dataRow.createCell(4).setCellValue(isendmail.get(i).getSubject());
            dataRow.createCell(5).setCellValue(isendmail.get(i).getFromEmail());
            dataRow.createCell(6).setCellValue(isendmail.get(i).getToEmail());
            dataRow.createCell(7).setCellValue(isendmail.get(i).getToCc());
            dataRow.createCell(8).setCellValue(isendmail.get(i).getSendFlag());
            dataRow.createCell(9).setCellValue(isendmail.get(i).getCreateTime());
            dataRow.createCell(10).setCellValue(isendmail.get(i).getSendTime());
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
            dataRow.getCell(10).setCellStyle(dataStyle);
            dataRow.setHeight((short) 1000);
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
        }

        response.setContentType("ms-vnd/excel");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
