package com.localbakery.api.search.service;

import com.localbakery.api.search.dto.PointDTO;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.model.StoreSearchRequest;
import com.localbakery.api.search.model.StoreSearchResponse;
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
