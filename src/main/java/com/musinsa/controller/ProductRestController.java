package com.musinsa.controller;

import com.musinsa.common.HttpStatusCustom;
import com.musinsa.common.ProductRequestDTO;
import com.musinsa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productsService;


    @GetMapping("/byCategoryLowestPrice")
    public ResponseEntity getByCategoryLowestPrice(){

        return ResponseEntity.ok().body("");
    }

    @GetMapping("/byBrandLowestPrice")
    public ResponseEntity getBrandLowestPrice(){

        return ResponseEntity.ok().body("");
    }

    @GetMapping("/byCategoryMaxMinPrice")
    public ResponseEntity getCategoryMaxMinPrice(){

        return ResponseEntity.ok().body("");
    }



    @PostMapping("/products")
    public ResponseEntity addProducts(@Valid @RequestBody ProductRequestDTO productRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }
        return ResponseEntity.ok().body(productsService.addProducts(productRequestDTO));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity updateProducts(@Valid @RequestBody ProductRequestDTO productRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(HttpStatusCustom.USER_PARAM_ERROR.getMessage());
        }
        return ResponseEntity.ok().body(productsService.updateProducts(productRequestDTO));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProducts(@PathVariable("productId") String productId){
        return ResponseEntity.ok().body(productsService.deleteProducts(productId));
    }

}
