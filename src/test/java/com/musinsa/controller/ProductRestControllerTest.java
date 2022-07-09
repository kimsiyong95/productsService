package com.musinsa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.common.request.ProductRequestDTO;
import com.musinsa.common.response.ResponseDTO;
import com.musinsa.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @DisplayName("모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API TEST")
    @Test
    public void getCategoryLowestPrice() throws Exception {
        mvc.perform(get("/categoryLowestPrice"))
                .andExpect(status().isOk());
    }


    @DisplayName("각 카테고리 이름으로 최소, 최대 가격 조회 API BadRequest Test")
    @Test
    public void getCategoryMaxMinPrice() throws Exception {
        mvc.perform(get("/categoryMaxMinPrice"))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("상품 추가 등록 테스트")
    @Test
    public void addProducts() throws Exception {
        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setBrandNm("A");
        requestDTO.setCategoryId(1L);
        requestDTO.setPrice(1000L);


        ResponseDTO responseDTO = new ResponseDTO().createResponseDTO(HttpStatus.CREATED, 3L);
        when(productService.addProducts(any())).thenReturn(responseDTO);

        mvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("3")));

    }

}