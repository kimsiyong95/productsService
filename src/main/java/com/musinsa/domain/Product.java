package com.musinsa.domain;

import com.musinsa.common.request.ProductRequestDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_product")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "brand_nm")
    private String brandNm;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product createProduct(ProductRequestDTO requestDTO, Category category){

        return Product.builder().brandNm(requestDTO.getBrandNm())
                                .price(requestDTO.getPrice())
                                .category(category)
                                .build();
    }


    public void updateProduct(ProductRequestDTO requestDTO, Category category){
        this.brandNm = requestDTO.getBrandNm();
        this.price = requestDTO.getPrice();
        this.category = category;
    }
}
