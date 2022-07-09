package com.musinsa.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
public class CategoryMaxMinPriceDTO {

    private String brandNm;
    private Long price;
    private String type;
    public CategoryMaxMinPriceDTO(String brandNm, Long price, String type) {
        this.brandNm = brandNm;
        this.price = price;
        this.type = type;
    }
}
