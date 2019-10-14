package com.swx.ad.constant;

import lombok.Getter;

@Getter
public enum CreativeMaterialType {

    JPG(1, "jpg"), BMP(2, "bmp"),
    MP4(21, "mp4"), AVI(22, "avi"),
    TXT(31, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
