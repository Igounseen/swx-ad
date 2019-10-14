package com.swx.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
