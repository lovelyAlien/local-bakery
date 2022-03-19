package com.localbakery.domain.controller;


import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @RequestMapping(value = "reviews/review", method = RequestMethod.POST)
    public ResponseContainer<Long> write(@AuthenticationPrincipal UserPrincipal userPrincipal,  @RequestParam("storeId") Long storeId , @RequestParam("storeName") String storeName, @RequestParam("rating") int rating, @RequestParam("contents") String contents) {

        Long reviewId= reviewService.write(userPrincipal, ReviewRequestVo.builder()
                .storeId(storeId)
                .storeName(storeName)
                .rating(rating)
                .contents(contents)
                .build());

        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviewId)
                .build();

    }


    @RequestMapping(value ="reviews/review/{id}", method = RequestMethod.PUT)
    public ResponseContainer<Long> modify(@PathVariable Long id, String contents){
        Long reviewId=reviewService.modify(id, contents);

        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviewId)
                .build();
    }

    @RequestMapping(value ="reviews/review/{id}", method = RequestMethod.DELETE)
    public ResponseContainer<Void> delete(@PathVariable Long id){
        reviewService.delete(id);

        return ResponseContainer.<Void>builder()
                .rMessage("OK")
                .rCode("200")
                .build();
    }

}
