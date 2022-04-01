package com.localbakery.domain.controller;


import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;



    @RequestMapping(value = "reviews/review", method = RequestMethod.POST)
    public ResponseContainer<Long> write(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody ReviewRequestVo reviewRequestVo) {


        Long reviewId= reviewService.write(userPrincipal, reviewRequestVo);
        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviewId)
                .build();

    }


    @RequestMapping(value = "reviews/{id}", method = RequestMethod.PUT)
    public ResponseContainer<Long> modify(@PathVariable Long id, @RequestBody ReviewRequestVo reviewRequestVo) {

        Long reviewId = reviewService.modify(id, reviewRequestVo);
        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviewId)
                .build();
    }

    @RequestMapping(value = "reviews/{id}", method = RequestMethod.DELETE)
    public ResponseContainer<Long> delete(@PathVariable Long id) {
        Long reviewId= reviewService.delete(id);

        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviewId)
                .build();
    }

    @RequestMapping(value = "stores/{id}/reviews", method = RequestMethod.GET)
    public ResponseContainer<List<Review>> findAll(@PathVariable Long id) {

        List<Review> reviews= reviewService.findAll(id);

        return ResponseContainer.<List<Review>> builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviews)
                .build();
    }

    @RequestMapping(value= "stores/{storeId}/reviews/{id}", method = RequestMethod.GET)
    public ResponseContainer<Review> findOne(@PathVariable Long id){

        Review review= reviewService.findOne(id);

        return ResponseContainer.<Review> builder()
                .rMessage("OK")
                .rCode("200")
                .rData(review)
                .build();
    }


}
