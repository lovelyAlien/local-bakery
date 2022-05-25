package com.localbakery.api.search.service;

import com.localbakery.api.search.model.StoreBo;
import org.springframework.data.domain.Slice;

public interface StoreQueryService {
    Slice<StoreBo> findAllByLocationNear(Double leftBottomLongitude, Double leftBottomLatitude, Double rightTopLongitude, Double rightTopLatitude);
}
