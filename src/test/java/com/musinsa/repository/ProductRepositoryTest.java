package com.musinsa.repository;

import com.musinsa.config.QueryDslConfigTest;
import com.musinsa.domain.Category;
import com.musinsa.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(QueryDslConfigTest.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        Category category = Category.builder()
                                    .categoryNm("모자")
                                    .build();
        categoryRepository.save(category);


        Product product = Product.builder()
                                 .category(category)
                                 .brandNm("A")
                                 .price(1000L)
                                 .build();

        productRepository.save(product);
    }

    @DisplayName("Repo 주입 테스트")
    @Test
    public void repoNotNullTest(){
        assertThat(productRepository).isNotNull();
        assertThat(categoryRepository).isNotNull();
    }


    @DisplayName("카테아이디/브랜명 상품 조회")
    @Test
    public void findByCategory_CategoryIdAndBrandNmTest(){
        Product findProduct = productRepository.findByCategory_CategoryIdAndBrandNm(1L, "A");


        assertThat(findProduct).isNotNull();
        assertThat(findProduct.getProductId()).isEqualTo(1L);
    }

    @DisplayName("상품의 브랜드를 변경 시 기존 브랜드 체크 조회")
    @Test
    public void findByProductIdNotAndBrandNmAndCategory_CategoryId(){
        Product findProduct = productRepository.findByProductIdNotAndBrandNmAndCategory_CategoryId(5L, "B", 3L);

        assertThat(findProduct).isNull();
    }


}