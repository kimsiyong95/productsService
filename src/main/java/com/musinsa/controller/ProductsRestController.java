package com.musinsa.controller;

import com.musinsa.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {
    private final ProductsService productsService;


}
