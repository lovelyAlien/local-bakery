package com.localbakery.api.controller.v1;

import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseContainer<SearchResponseVo> search(@RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude) {
        SearchResponseVo searchResponseVo = searchService.search(SearchRequestVo.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build());
        return ResponseContainer.<SearchResponseVo>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(searchResponseVo)
                .build();

    }
}
