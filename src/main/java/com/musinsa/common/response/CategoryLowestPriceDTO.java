package com.musinsa.common.response;


import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Long> totalPrice = new AtomicReference<>(0L);

        list.forEach(dto -> {totalPrice.updateAndGet(v -> v + dto.getPrice());});

        return totalPrice.get();
    }
}
