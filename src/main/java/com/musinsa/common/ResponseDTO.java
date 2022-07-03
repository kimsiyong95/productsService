package com.musinsa.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ResponseDTO<T> {
    int code;
    String message;

    T data;
}
