package com.vms.server.util;

import com.vms.server.exception.CustomException;
import com.vms.server.exception.ErrorCode;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class FileValidation {

    public static void fileValidate(File file) throws CustomException {
        Tika tika = new Tika();
        String fileName = file.getName();
        String extension = FilenameUtils.getExtension(fileName).toLowerCase();
        String baseName = FilenameUtils.getBaseName(fileName);

        // 파일의 MIME 타입 검사
        String detectedType;
        try {
            detectedType = tika.detect(file);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.DETTECT_FAIL);
        }

        // 허용된 MIME 타입 목록
        List<String> allowedMimeTypes = Arrays.asList(
                "text/csv",
                "application/zip",
                "text/plain",
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.ms-powerpoint",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                "application/pdf"
        );

        if (!allowedMimeTypes.contains(detectedType)) {
            //file.delete();
            throw new CustomException(ErrorCode.INVALID_EXTEND);
        }

        //파일명 크기 제한
        long fileSize = file.length();

        long maxSize = 5 * 1024 * 1024; // 5MB
        if (fileSize > maxSize) {
            file.delete();
            throw new CustomException(ErrorCode.FILE_SIZE_ERROR);
        }

        List<String> restrictedPatterns = Arrays.asList(
                "<script>", // 예를 들어, JavaScript 시작 태그
                "eval(",
                "base64_decode(",
                "onload=",
                ";&nbsp;cat .*",
                "; rm .*",
                "vbscript:"
        );
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String pattern : restrictedPatterns) {
            if (content.contains(pattern)) {
                file.delete();
                throw new CustomException(ErrorCode.RESTRICTED_CONTENT);
            }
        }
    }
    }
