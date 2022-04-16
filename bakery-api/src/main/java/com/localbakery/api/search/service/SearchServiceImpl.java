package com.localbakery.api.search.service;

import com.localbakery.api.controller.dto.PointDTO;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.search.model.StoreSearchRequest;
import com.localbakery.search.model.StoreSearchResponse;
import com.localbakery.search.service.StoreSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final StoreSearchService storeSearchService;

    @Override
    public SearchResponseVo search(PointDTO searchRequest) {
        StoreSearchRequest storeSearchRequest = StoreSearchRequest.builder()
                .leftBottomLatitude(searchRequest.getLeftBottomLatitude())
                .leftBottomLongitude(searchRequest.getLeftBottomLongitude())
                .rightTopLatitude(searchRequest.getRightTopLatitude())
                .rightTopLongitude(searchRequest.getRightTopLongitude())
                .build();
        StoreSearchResponse response = storeSearchService.search(storeSearchRequest);
        return SearchResponseVo.builder()
                .stores(response.getStores())
                .build();
    }
}
