package com.vms.server.voc.dto.request;

import com.vms.server.admin.dto.FileUploadRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class VocSaveRequest {
    private String plant;
    private String systemName;
    private String revisionNo;
    private String receptionDept; // 부서 추후 처리
    private String requiredResponseDate;
    private String remark;
    private String personInCharge;
    private String requirement;
    private String classification;
    private MultipartFile file;

    public FileUploadRequest makeFileRequest(String qmsNumber) {
        return FileUploadRequest.builder()
                .file(file)
                .plant(plant)
                .systemName(systemName)
                .systemNameMtype("VOC")
                .systemNameStype("VOC")
                .qmsNumber(qmsNumber)
                .revisionNo(revisionNo)
                .build();
    }
}
