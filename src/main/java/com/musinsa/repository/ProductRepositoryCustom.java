package com.musinsa.repository;

import com.musinsa.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    public List<Product> byCategoryLowestPrice();
}
