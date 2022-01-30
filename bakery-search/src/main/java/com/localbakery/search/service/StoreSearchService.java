package com.localbakery.search.service;

import com.localbakery.search.model.StoreSearchRequest;
import com.localbakery.search.model.StoreSearchResponse;

public interface StoreSearchService {
    StoreSearchResponse search(StoreSearchRequest request);
}
