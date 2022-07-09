package com.musinsa.controller;

import com.musinsa.common.HttpStatusCustom;
import com.musinsa.common.request.ProductRequestDTO;
import com.musinsa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productsService;


    @GetMapping("/categoryLowestPrice")
    public ResponseEntity getCategoryLowestPrice(){
        return ResponseEntity.ok().body(productsService.getCategoryLowestPrice());
    }

    @GetMapping("/brandLowestPrice")
    public ResponseEntity getBrandLowestPrice(){
        return ResponseEntity.ok().body(productsService.getBrandLowestPrice());
    }

    @GetMapping("/categoryMaxMinPrice")
    public ResponseEntity getCategoryMaxMinPrice(@RequestParam(required = false) String categoryNm){
        if(!StringUtils.hasText(categoryNm)){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }

        return ResponseEntity.ok().body(productsService.getCategoryMaxMinPrice(categoryNm));
    }



    @PostMapping("/products")
    public ResponseEntity addProducts(@Valid @RequestBody ProductRequestDTO productRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }
        return ResponseEntity.ok().body(productsService.addProducts(productRequestDTO));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity updateProducts(@Valid @RequestBody ProductRequestDTO productRequestDTO
            , BindingResult bindingResult
            , @PathVariable(required = false) String productId){

        if(bindingResult.hasErrors()||!StringUtils.hasText(productId)){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }
        return ResponseEntity.ok().body(productsService.updateProducts(productRequestDTO, productId));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProducts(@PathVariable(required = false) String productId){
        if(!StringUtils.hasText(productId)){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }

        return ResponseEntity.ok().body(productsService.deleteProducts(productId));
    }

}
