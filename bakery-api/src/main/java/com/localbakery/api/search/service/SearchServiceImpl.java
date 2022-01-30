package com.localbakery.api.search.service;

import com.localbakery.api.search.model.SearchRequestVo;
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
    public SearchResponseVo search(SearchRequestVo searchRequest) {
        StoreSearchRequest storeSearchRequest = StoreSearchRequest.builder()
                        .build();
        StoreSearchResponse response = storeSearchService.search(storeSearchRequest);
        return SearchResponseVo.builder()
                .build();
    }

}
