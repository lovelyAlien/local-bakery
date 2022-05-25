package com.localbakery.api.search.service;

import com.localbakery.api.search.model.StoreSearchRequest;
import com.localbakery.api.search.model.StoreSearchResponse;

public interface StoreSearchService {
    StoreSearchResponse search(StoreSearchRequest request);
}
