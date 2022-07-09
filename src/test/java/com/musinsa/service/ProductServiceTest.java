package com.musinsa.service;

import com.musinsa.common.HttpStatusCustom;
import com.musinsa.common.request.ProductRequestDTO;
import com.musinsa.domain.Category;
import com.musinsa.domain.Product;
import com.musinsa.repository.CategoryRepository;
import com.musinsa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;


    @DisplayName("카테고리 별 최소 최대 조회 API Exception Test")
    @Test
    public void getCategoryMaxMinPriceTest(){
        when(categoryRepository.findByCategoryNm(any())).thenReturn(null);

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.getCategoryMaxMinPrice("상의");
        });

        assertThat(error.getMessage()).isEqualTo(HttpStatusCustom.USER_PARAM_ERROR.getMessage());

    }

    @DisplayName("상품 등록 API CATE_NOT_FOUND Exception Test")
    @Test
    public void addProductsCateNotFoundExceptionTest(){
        when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.addProducts(new ProductRequestDTO());
        });

        assertThat(error.getMessage()).isEqualTo(HttpStatusCustom.CATE_NOT_FOUND.getMessage());
    }

    @DisplayName("상품 등록 API BRAND_ALREADY_EXIST Exception Test")
    @Test
    public void addProductsBrandAlreadyExistTest(){

        when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(Category.builder().build()));
        when(productRepository.findByCategory_CategoryIdAndBrandNm(any(),any())).thenReturn(Product.builder().build());

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.addProducts(new ProductRequestDTO());
        });

        assertThat(error.getMessage()).isEqualTo(HttpStatusCustom.BRAND_ALREADY_EXIST.getMessage());
    }

}