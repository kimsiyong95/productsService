package com.musinsa.common.response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class BrandLowestPriceDTO {
    private String branNm;
    private Long price;

    public BrandLowestPriceDTO(String branNm, Long price){
        this.branNm = branNm;
        this.price = price;
    }
}
