package com.musinsa.repository;

import com.musinsa.common.response.BrandLowestPriceDTO;
import com.musinsa.common.response.CategoryLowestPriceDTO;
import com.musinsa.common.response.CategoryMaxMinPriceDTO;
import com.musinsa.domain.Product;
import com.musinsa.domain.QCategory;
import com.musinsa.domain.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QProduct qProduct;

    private final QProduct qProductSub;

    private final QCategory qCategory;


    public ProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
        this.qProduct = new QProduct("p1");
        this.qProductSub = new QProduct("p2");
        this.qCategory = QCategory.category;
    }

//
    @Override
    public List<CategoryLowestPriceDTO> categoryLowestPrice() {
          return queryFactory.select(Projections.constructor(CategoryLowestPriceDTO.class, qCategory.categoryNm ,qProduct.brandNm, qProduct.price))
                     .from(qProduct)
                     .innerJoin(qProduct.category, qCategory)
                     .where(
                             qProduct.price.eq(
                                     select(qProductSub.price.min())
                                           .from(qProductSub)
                                           .where(qProductSub.category.eq(qProduct.category))
                             )
                     )
                    .groupBy(qCategory.categoryId)
                    .fetch();
    }

    @Override
    public BrandLowestPriceDTO brandLowestPrice() {
        return queryFactory.select(Projections.constructor(BrandLowestPriceDTO.class,qProduct.brandNm, qProduct.price.sum()))
                    .from(qProduct)
                    .groupBy(qProduct.brandNm)
                    .orderBy(qProduct.price.sum().asc())
                    .limit(1)
                    .fetchOne();
    }

    @Override
    public CategoryMaxMinPriceDTO categoryMaxMinPrice(String categoryNm) {
        return null;
    }
}
