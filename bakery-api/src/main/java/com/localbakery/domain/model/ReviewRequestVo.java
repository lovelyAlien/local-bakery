package com.localbakery.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
