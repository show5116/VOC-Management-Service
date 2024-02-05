package com.vms.server.HRIF;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vms.server.admin.repository.jpa.AdminAdmDeptRepository;
import com.vms.server.admin.repository.jpa.AdminAdmEmployeeRepository;
import com.vms.server.admin.repository.jpa.AdminAdmJobRepository;
import com.vms.server.admin.repository.jpa.AdminAdmPlantRepository;
import com.vms.server.admin.repository.jpa.AdminAdmPositionRepository;
import com.vms.server.admin.repository.jpa.AdminAdmWorkRepository;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.domain.entity.adm.AdmDept;
import com.vms.server.domain.entity.adm.AdmEmployee;
import com.vms.server.domain.entity.adm.AdmMyJob;
import com.vms.server.domain.entity.adm.AdmPlant;
import com.vms.server.domain.entity.adm.AdmPosition;
import com.vms.server.domain.entity.adm.AdmWork;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class HRIFService {
   private final AdminAdmDeptRepository admDeptRepository;
    private final AdminAdmPositionRepository admPositionRepository;
    private final AdminAdmWorkRepository admWorkRepository;
    private final AdminAdmJobRepository admJobRepository;
    private final AdminAdmEmployeeRepository admEmployeeRepository;
    private final AdminAdmPlantRepository admPlantRepository;
    private final AdminSysSystemCodeDataRepository sysSystemCodeDataRepository;

    @Scheduled(cron = "0 0 3 * * *")
    public void updateHRInfo(){
        String token = "";

        try {
             List<SysSystemCodeData> hrSysCodeDatas = sysSystemCodeDataRepository.findByPlantAndTableNameOrderByCodeSeqAsc("SiliconMitus", "HR_IF");
            String tokenUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("TOKEN")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String company =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("COMPANY")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String deptUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("DEPT")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String positionUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("POSITION")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String workUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("WORK")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String jobUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("JOB")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);
            String employeeUrl =  hrSysCodeDatas.stream().filter(syscode -> syscode.getCodeName().equals("EMPLOYEE")).map(SysSystemCodeData::getDescription).findFirst()
                    .orElse(null);

            token = getTokenJoson(tokenUrl, company);
            // 부서 정보
            List<Map<String, Object>> deptList = callApiWithBearerToken(deptUrl, token, company);
            // Position
            List<Map<String, Object>> positionList = callApiWithBearerToken(positionUrl, token, company);
            // Work
            List<Map<String, Object>> workList = callApiWithBearerToken(workUrl, token, company);
            // job
            List<Map<String, Object>> jobList = callApiWithBearerToken(jobUrl, token, company);
            //employee
            List<Map<String, Object>>  employeeList = callApiWithBearerToken(employeeUrl, token, company);

            deptReader(deptList);
            positionReader(positionList);
            workReader(workList);
            jobReader(jobList);
            employeeReader(employeeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void employeeReader(List<Map<String, Object>> employeeList) {
        List<AdmEmployee> admEmployeeEntityList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate today = LocalDate.now();
//        List<AdmUserInfo> beforeUserList = admUserInfoRepository.findByExternalFlag("N");
//        admUserInfoRepository.deleteAllByExternalFlag("N");
        List<AdmPlant> plantList = admPlantRepository.findAll();
        for (Map<String, Object> map : employeeList) {
            String userId = (String) map.get("EMP_CODE");
            String empName = (String) map.get("EMP_NAME");
            String empEnName = (String) map.get("EMP_EN_NAME");
            String empEmailName = (String) map.get("EMP_EMAIL_NAME");
            String solarYn = (String) map.get("SOLAR_YN");
            String birth = (String) map.get("BIRTH");
            String hireDate = (String) map.get("HIRE_DATE");
            String outDate = (String) map.get("OUT_DATE");
            String posId = (String) map.get("POS_ID");
            Integer secLevel = (Integer) map.get("SEC_LEVEL");
            Integer empLevel = (Integer)  map.get("EMP_LEVEL");
            String empStatus = (String) map.get("EMP_STATUS");
            String mobile = (String) map.get("MOBILE");
            String phone = (String) map.get("PHONE");
            String fax = (String) map.get("FAX");
            String email = (String) map.get("EMAIL");
            String cdZipCode = (String) map.get("CD_ZIPCODE");
            String homeAddr = (String) map.get("HOME_ADDR");
            String homeAddr2 = (String) map.get("HOME_ADDR2");
            String homeTel = (String) map.get("HOME_TEL");
            String ifdata = (String) map.get("IFDATA");

            if(map.containsKey("HIRE_DATE")) {
                if(hireDate != null &&!hireDate.equals("")) {
                    LocalDate inputDate = LocalDate.parse(hireDate, formatter);
                    if (inputDate.isAfter(today)) continue;
                }
            }
            if( map.containsKey("IFDATA") ) {
                if(ifdata.equals("N"))continue;
            }
            if(map.containsKey("EMP_STATUS")) {
                if(empStatus.equals("L")){
                    empStatus ="W";
                }
            }
            if(map.containsKey("OUT_DATE") && map.containsKey("EMP_STATUS")){
            if(outDate != null && !outDate.equals("")){
                LocalDate inputDate = LocalDate.parse(outDate, formatter);
                if ((inputDate.isEqual(today) || inputDate.isAfter(today))  && empStatus.equals("R")){
                    empStatus = "W";
                }
            }
            }
                admEmployeeEntityList.add(new AdmEmployee(userId, "20", empName, empEnName, empEmailName, "N", solarYn,
                        birth, hireDate, outDate, "", posId, empLevel, secLevel, empStatus, mobile, phone,
                        fax, email, "", homeAddr, homeAddr2, "", homeTel, cdZipCode, ifdata) );
            }
        for (AdmEmployee admEmployee : admEmployeeEntityList) {
            admEmployeeRepository.save(admEmployee);
        }
    }
    public void jobReader(List<Map<String, Object>> jobList) {
        List<AdmMyJob> admMyJobEntityList = new ArrayList<AdmMyJob>();
        for (Map<String, Object> map : jobList) {
            String defaultYn = (String) map.get("DEFAULT_YN");
            if (!defaultYn.equals("Y")) continue;
            String empCode = (String) map.get("EMP_CODE");
            String deptId = (String) map.get("DEPT_ID");
            String ofcId = (String) map.get("OFC_ID");
            if (deptId == null || deptId.equals("")) {
                deptId = "EMPTY_DEPT";
            }
            admMyJobEntityList.add(new AdmMyJob(empCode, deptId, ofcId, defaultYn));
        }
        admJobRepository.deleteAll();
        for (AdmMyJob admMyJob : admMyJobEntityList) {
            admJobRepository.save(admMyJob);
        }
    }

    public void workReader(List<Map<String, Object>> workList) {
        List<AdmWork> admWorkEntityList = new ArrayList<AdmWork>();
        for (Map<String, Object> map : workList) {
            String ofcCd = (String) map.get("OFC_CD");
            String ofcName = (String) map.get("OFC_NAME");
            int ofcOrder = (int) (double) map.get("OFC_ORDER");

            admWorkEntityList.add(new AdmWork(ofcCd, ofcName, ofcOrder));
        }
        admWorkRepository.deleteAll();
        for (AdmWork admWork : admWorkEntityList) {
          admWorkRepository.save(admWork);
        }

    }

    public void positionReader(List<Map<String, Object>> positionList) {
        List<AdmPosition> admPositionsEntityList = new ArrayList<AdmPosition>();
        for (Map<String, Object> map : positionList) {
            String posId = (String) map.get("POS_ID");
            String posName = (String) map.get("POS_NAME");
            int posOrder = (int) (double) map.get("POS_ORDER");

            admPositionsEntityList.add(new AdmPosition(posId, posName,posOrder));

        }
        admPositionRepository.deleteAll();
        for (AdmPosition admPosition : admPositionsEntityList) {
         admPositionRepository.save(admPosition);
        }

    }

    public void deptReader(List<Map<String, Object>> deptList) {
        List<AdmDept> admDeptEntityList = new ArrayList<AdmDept>();

        for (Map<String, Object> map : deptList) {
            String deptId = (String) map.get("DEPT_ID");
            String deptName = (String) map.get("DEPT_NAME");
            int deptOrder = (int) (double) map.get("DEPT_ORDER");
            String upDeptIndex = (String) map.get("UP_DEPT_IDX");
            if (upDeptIndex == null || upDeptIndex.equals("")) {
                upDeptIndex = "ROOT";
            }
            String deptStatus = (String) map.get("DEPT_STATUS");
            int deptDepth = (int) (double) map.get("DEPT_DEPTH");
            admDeptEntityList.add(new AdmDept(deptId, deptStatus, deptName, deptOrder, upDeptIndex, deptDepth));
        }
        admDeptRepository.deleteAll();
        for (AdmDept admDept : admDeptEntityList) {
            admDeptRepository.save(admDept);
        }
    }

    public List<Map<String, Object>> callApiWithBearerToken(String urlParam, String token, String company) throws IOException {
        List<Map<String, Object>> result = null;
        URL url = new URL(urlParam);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setDoOutput(true);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("company", company);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8") );
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> resultMap = gson.fromJson(sb.toString(), mapType);
        Type listMapType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        result = gson.fromJson((String) resultMap.get("result"), listMapType);

        return result;
    }

    public String getTokenJoson(String urlParam, String company) throws IOException {
        String result = "";
        StringBuilder urlBuilder = new StringBuilder(urlParam);

        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        List<SysSystemCodeData> systemIdDatas = sysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName("SiliconMitus","HR_IF","SYSTEMID");
        List<SysSystemCodeData> systemDatas = sysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName("SiliconMitus","HR_IF","SYSTEM");


        String systemId = "";
        String system = "";
        if(systemIdDatas != null && !systemIdDatas.isEmpty())systemId = systemIdDatas.get(0).getDescription();
        if(systemDatas != null && !systemDatas.isEmpty())system = systemDatas.get(0).getDescription();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("company", company);
        jsonObject.addProperty("systemid", systemId);
        jsonObject.addProperty("system", system);


        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> resultMap = gson.fromJson(sb.toString(), type);
        result = (String) resultMap.get("token");
        return result;
    }
}
