package com.localbakery.api.search.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchRequestVo {
    private Double longitude;
    private Double latitude;
    private Double longitude2;
    private Double latitude2;
}
