package com.localbakery.api.search.service;

import com.localbakery.api.search.model.StoreBo;
import com.localbakery.api.search.model.StoreSearchRequest;
import com.localbakery.api.search.model.StoreSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreSearchServiceImpl implements StoreSearchService {

    private final StoreQueryService storeQueryService;

    @Override
    public StoreSearchResponse search(StoreSearchRequest request) {
        Slice<StoreBo> storeBoList = storeQueryService.findAllByLocationNear(request.getLeftBottomLongitude(), request.getLeftBottomLatitude(), request.getRightTopLongitude(), request.getRightTopLatitude());
        return StoreSearchResponse.builder()
                .stores(storeBoList)
                .build();
    }
}
