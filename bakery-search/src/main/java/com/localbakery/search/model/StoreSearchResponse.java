package com.localbakery.search.model;

import com.localbakery.query.model.StoreBo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Builder
@Getter
public class StoreSearchResponse {
    private Slice<StoreBo> stores;
}
