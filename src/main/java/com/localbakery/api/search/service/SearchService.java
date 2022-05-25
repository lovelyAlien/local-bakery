package com.localbakery.api.search.service;

import com.localbakery.api.search.dto.PointDTO;
import com.localbakery.api.search.model.SearchResponseVo;

public interface SearchService {

    SearchResponseVo search(PointDTO searchRequest);
}
