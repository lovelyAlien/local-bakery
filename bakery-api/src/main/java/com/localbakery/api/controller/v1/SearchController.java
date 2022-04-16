package com.localbakery.api.controller.v1;

import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.controller.dto.PointDTO;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseContainer<SearchResponseVo> search(PointDTO pointdto) {
        SearchResponseVo searchResponseVo = searchService.search(pointdto);
        return ResponseContainer.<SearchResponseVo>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(searchResponseVo)
                .build();
    }
}
