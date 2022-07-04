package com.musinsa.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_category")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_nm")
    private String categoryNm;
}
