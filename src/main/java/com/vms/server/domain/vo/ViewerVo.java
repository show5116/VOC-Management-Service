package com.vms.server.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewerVo {

    private byte[] fileData;
    private String extension;

    public ViewerVo(byte[] fileData, String extension) {
        this.fileData = fileData;
        this.extension = extension;
    }
}
