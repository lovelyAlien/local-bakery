package com.localbakery.search.service;

import com.localbakery.query.model.StoreBo;
import com.localbakery.query.service.StoreQueryService;
import com.localbakery.search.model.StoreSearchRequest;
import com.localbakery.search.model.StoreSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreSearchServiceImpl implements StoreSearchService {

    private final StoreQueryService storeQueryService;

    @Override
    public StoreSearchResponse search(StoreSearchRequest request) {
        Slice<StoreBo> storeBoList = storeQueryService.findAllByLocationNear(request.getLongitude(), request.getLatitude());
        return StoreSearchResponse.builder()
                .stores(storeBoList)
                .build();
    }
}
