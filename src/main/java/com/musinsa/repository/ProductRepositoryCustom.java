package com.musinsa.repository;

import com.musinsa.common.response.BrandLowestPriceDTO;
import com.musinsa.common.response.CategoryLowestPriceDTO;
import com.musinsa.common.response.CategoryMaxMinPriceDTO;

import java.util.List;

public interface ProductRepositoryCustom {

    public List<CategoryLowestPriceDTO> categoryLowestPrice();

    public BrandLowestPriceDTO brandLowestPrice();

    public List<CategoryMaxMinPriceDTO> categoryMaxMinPrice(String categoryNm);
}
