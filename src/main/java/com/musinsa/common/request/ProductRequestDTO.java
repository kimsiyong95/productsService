package com.musinsa.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductRequestDTO {

    private Long productId;

    @NotNull
    private Long categoryId;

    @NotNull
    private String brandNm;

    @NotNull
    private Long price;

}
