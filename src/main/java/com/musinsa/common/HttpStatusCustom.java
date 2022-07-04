package com.musinsa.common;

import lombok.Getter;

@Getter
public enum HttpStatusCustom {
    USER_PARAM_ERROR(400, "사용자 파라미터 오류");

    private int code;
    private String message;

    HttpStatusCustom(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
