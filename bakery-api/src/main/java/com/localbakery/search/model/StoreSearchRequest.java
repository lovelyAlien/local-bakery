package com.localbakery.search.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class  StoreSearchRequest {

    private Double longitude;
    private Double latitude;
    private Double longitude2;
    private Double latitude2;
}
