package com.musinsa.repository;


import com.musinsa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    public Product findByCategory_CategoryIdAndBrandNm(Long categoryId, String brandNm);
    public Product findByProductIdNotAndBrandNmAndCategory_CategoryId(Long productId, String brandNm, Long categoryId);
}
