package com.musinsa.common.request;

import javax.validation.constraints.NotEmpty;

public class ProductRequestDTO {

    private Long productId;

    @NotEmpty
    private Long categoryId;

    @NotEmpty
    private String brandNm;

    @NotEmpty
    private Long price;

}
