package com.musinsa.repository;

import com.musinsa.config.QueryDslConfigTest;
import com.musinsa.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(QueryDslConfigTest.class)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("카테고리 Repo 주입 테스트")
    @Test
    public void productRepositoryNotNullTest(){
        assertThat(categoryRepository).isNotNull();
    }

    @DisplayName("카테고리 이름 조회")
    @Test
    public void categoryByNameTest(){
        Category category = Category.builder()
                                    .categoryNm("상의")
                                    .build();

        categoryRepository.save(category);


        Category findCategory = categoryRepository.findByCategoryNm("상의");

        assertThat(findCategory).isNotNull();
        assertThat(findCategory.getCategoryNm()).isEqualTo("상의");
    }
}