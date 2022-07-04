package com.musinsa.repository;

import com.musinsa.domain.Product;
import com.musinsa.domain.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QProduct qProduct;


    public ProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
        this.qProduct = QProduct.product;
    }


    @Override
    public List<Product> byCategoryLowestPrice() {
        return null;
    }
}
