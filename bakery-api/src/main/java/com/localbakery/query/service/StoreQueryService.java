package com.localbakery.query.service;

import com.localbakery.query.model.StoreBo;
import org.springframework.data.domain.Slice;

public interface StoreQueryService {
    Slice<StoreBo> findAllByLocationNear(Double leftBottomLongitude, Double leftBottomLatitude, Double rightTopLongitude, Double rightTopLatitude);
}
