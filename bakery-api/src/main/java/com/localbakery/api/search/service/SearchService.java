package com.localbakery.api.search.service;

import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;

public interface SearchService {

    SearchResponseVo search(SearchRequestVo searchRequest);
}
