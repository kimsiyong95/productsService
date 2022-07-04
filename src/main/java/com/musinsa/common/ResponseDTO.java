package com.musinsa.common;

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


    public ResponseDTO createResponseDTO(HttpStatus httpStatus, T data){
        this.code = httpStatus.value();
        this.message = httpStatus.name();
        this.data = data;

        return this;
    }

}
