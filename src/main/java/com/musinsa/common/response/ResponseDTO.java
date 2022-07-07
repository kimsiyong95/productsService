package com.musinsa.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    int code;
    String message;

    @JsonInclude(NON_NULL)
    T data;

    @JsonInclude(NON_NULL)
    Long totalPrice;


    public ResponseDTO createResponseDTO(HttpStatus httpStatus, T data){
        this.code = httpStatus.value();
        this.message = httpStatus.name();
        this.data = data;

        return this;
    }

    public ResponseDTO createResponseDTO(HttpStatus httpStatus, T data, Long totalPrice){
        this.code = httpStatus.value();
        this.message = httpStatus.name();
        this.data = data;
        this.totalPrice = totalPrice;
        return this;
    }

}
