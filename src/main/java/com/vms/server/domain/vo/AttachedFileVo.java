package com.vms.server.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachedFileVo {
    File file;
    String fileName;
    String backupFileName;

    public AttachedFileVo(File file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

}
