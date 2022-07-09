package com.musinsa.service;

import com.musinsa.common.HttpStatusCustom;
import com.musinsa.common.response.CategoryLowestPriceDTO;
import com.musinsa.common.request.ProductRequestDTO;
import com.musinsa.common.response.ResponseDTO;
import com.musinsa.domain.Category;
import com.musinsa.domain.Product;
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

        return new ResponseDTO().createResponseDTO(HttpStatus.OK, productsRepository.categoryMaxMinPrice(categoryNm));
    }


    @Transactional
    public ResponseDTO addProducts(ProductRequestDTO productRequestDTO){
        Optional<Category> findCategory = categoryRepository.findById(productRequestDTO.getCategoryId());

        if(!findCategory.isPresent()){
            throw new IllegalArgumentException(HttpStatusCustom.CATE_NOT_FOUND.getMessage());
        }

        Optional<Product> findProduct = Optional.ofNullable(
                productsRepository.findByCategory_CategoryIdAndBrandNm(
                        findCategory.get().getCategoryId()
                        , productRequestDTO.getBrandNm()));
        
        if(findProduct.isPresent()){
            throw new IllegalArgumentException(HttpStatusCustom.BRAND_ALREADY_EXIST.getMessage());
        }

        Product saveProduct = productsRepository.save(Product.createProduct(productRequestDTO, findCategory.get()));
        
        return new ResponseDTO().createResponseDTO(HttpStatus.CREATED, saveProduct.getProductId());
    }

    @Transactional
    public ResponseDTO updateProducts(ProductRequestDTO productRequestDTO, String productId){
        Optional<Product> findProduct;

        try {
             Optional<Category> findCategory = categoryRepository.findById(productRequestDTO.getCategoryId());

             if(!findCategory.isPresent()){ // 수정하려는 데이터에 카테고리가 없을 시
                 throw new IllegalArgumentException(HttpStatusCustom.CATE_NOT_FOUND.getMessage());
             }

             findProduct = productsRepository.findById(Long.valueOf(productId));

             if(!findProduct.isPresent()){ // 수정하려는 상품에 데이터가 없을 시
                 throw new IllegalArgumentException(HttpStatusCustom.PRODUCT_NOT_FOUND.getMessage());
             }

            Optional<Product> dupleProduct = Optional.ofNullable(productsRepository.findByProductIdNotAndBrandNmAndCategory_CategoryId(Long.valueOf(productId)
                    , productRequestDTO.getBrandNm()
                    , productRequestDTO.getCategoryId()));


            if(dupleProduct.isPresent()){ // 수정하려는 상품의 브랜드를 변경 시 기존 브랜드가 있을 경우
                throw new IllegalArgumentException(HttpStatusCustom.BRAND_ALREADY_EXIST.getMessage());
            }

            findProduct.get().updateProduct(productRequestDTO, findCategory.get());

        }catch (NumberFormatException e){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }


        return new ResponseDTO().createResponseDTO(HttpStatus.OK, findProduct.get().getProductId());
    }
    @Transactional
    public ResponseDTO deleteProducts(String productId){
        try {
            Optional<Product> findById = productsRepository.findById(Long.valueOf(productId));

            if(!findById.isPresent()){
                throw new IllegalArgumentException(HttpStatusCustom.PRODUCT_NOT_FOUND.getMessage());
            }

            productsRepository.delete(findById.get());
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }

        return new ResponseDTO().createResponseDTO(HttpStatus.OK, productId);
    }
}
