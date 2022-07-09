package com.musinsa.common;

import lombok.Getter;

@Getter
public enum HttpStatusCustom {
    USER_PARAM_ERROR(400, "사용자 파라미터 오류"),
    CATE_NOT_FOUND(400, "존재하지 않은 카테고리"),

    PRODUCT_NOT_FOUND(400, "존재하지 않은 상품"),
    BRAND_ALREADY_EXIST(400, "이미 존재하는 브랜드 상품");

    private int code;
    private String message;

    HttpStatusCustom(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
