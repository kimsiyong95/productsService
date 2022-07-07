package com.musinsa.service;

import com.musinsa.common.HttpStatusCustom;
import com.musinsa.common.response.CategoryLowestPriceDTO;
import com.musinsa.common.request.ProductRequestDTO;
import com.musinsa.common.response.ResponseDTO;
import com.musinsa.domain.Category;
import com.musinsa.repository.CategoryRepository;
import com.musinsa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productsRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ResponseDTO getCategoryLowestPrice(){
        List<CategoryLowestPriceDTO> categoryLowestPriceDTO = productsRepository.categoryLowestPrice();
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, categoryLowestPriceDTO, CategoryLowestPriceDTO.totalPrice(categoryLowestPriceDTO));
    }

    @Transactional(readOnly = true)
    public ResponseDTO getBrandLowestPrice(){
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, productsRepository.brandLowestPrice());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getCategoryMaxMinPrice(String categoryNm){
        Optional<Category> findCategory = Optional.ofNullable(categoryRepository.findByCategoryNm(categoryNm));

        if(!findCategory.isPresent()){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }



        return new ResponseDTO().createResponseDTO(HttpStatus.OK, findCategory);
    }



    public ResponseDTO addProducts(ProductRequestDTO productRequestDTO){
        return new ResponseDTO().createResponseDTO(HttpStatus.CREATED, "");
    }

    public ResponseDTO updateProducts(ProductRequestDTO productRequestDTO){
        return new ResponseDTO().createResponseDTO(HttpStatus.CREATED, "");
    }

    public ResponseDTO deleteProducts(String productId){
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, "");
    }
}
