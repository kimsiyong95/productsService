package com.musinsa.common.response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class CategoryLowestPriceDTO {

    private String categoryNm;
    private String branNm;
    private Long price;

    public CategoryLowestPriceDTO(String categoryNm, String branNm, Long price){
        this.categoryNm = categoryNm;
        this.branNm = branNm;
        this.price = price;
    }


    public static Long totalPrice(List<CategoryLowestPriceDTO> list){
        Long totalPrice = 0L;

        for(CategoryLowestPriceDTO dto : list){
            totalPrice+=dto.getPrice();
        }
        return totalPrice;
    }
}
