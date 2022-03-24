package com.localbakery.domain.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewRequestVo {
    private Long storeId;

    private String storeName;

    private float rating;

    private String contents;

}
