package com.localbakery.api.search.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class  StoreSearchRequest {

    private Double leftBottomLongitude;
    private Double leftBottomLatitude;
    private Double rightTopLongitude;
    private Double rightTopLatitude;
}
