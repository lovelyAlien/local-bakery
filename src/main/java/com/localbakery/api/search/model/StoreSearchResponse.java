package com.localbakery.api.search.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Builder
@Getter
public class StoreSearchResponse {
    private Slice<StoreBo> stores;
}
