package com.musinsa.service;

import com.musinsa.common.ProductRequestDTO;
import com.musinsa.common.ResponseDTO;
import com.musinsa.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;


    public ResponseDTO getByCategoryLowestPrice(){
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, "");
    }

    public ResponseDTO getBrandLowestPrice(){
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, "");
    }

    public ResponseDTO getCategoryMaxMinPrice(){
        return new ResponseDTO().createResponseDTO(HttpStatus.OK, "");
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
