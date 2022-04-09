package com.localbakery.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewRequestVo {

    private Long storeId;

    private String storeName;

    private float rating;

    private String contents;

    private String specials;

    private String recommends;

}
