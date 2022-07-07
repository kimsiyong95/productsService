package com.musinsa.repository;

import com.musinsa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    public Category findByCategoryNm(String categoryNm);
}
