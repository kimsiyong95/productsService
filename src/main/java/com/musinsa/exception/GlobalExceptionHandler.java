package com.musinsa.exception;

import com.musinsa.common.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentExceptionHandler(IllegalArgumentException e){

        ResponseDTO responseDTO = ResponseDTO.builder()
                                             .code(HttpStatus.BAD_REQUEST.value())
                                             .message(e.getMessage())
                                             .build();

        return ResponseEntity.badRequest().body(responseDTO);
    }
}
