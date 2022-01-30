package com.localbakery.api.controller.v1;

import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @RequestMapping("search")
    public ResponseContainer search(@ModelAttribute SearchRequestVo searchRequest) {
        SearchResponseVo searchResponseVo = searchService.search(searchRequest);
        return ResponseContainer.ok("hello");

    }
}
