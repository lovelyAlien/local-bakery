package com.localbakery.mypage.controller;

import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final ReviewService reviewService;

    @RequestMapping(value = "mypage/reviews", method = RequestMethod.GET)
    public ResponseContainer<List<Review>> findUserReviews(@AuthenticationPrincipal UserPrincipal userPrincipal) {


        List<Review> reviews= reviewService.findUserReviews(userPrincipal);
        return ResponseContainer.<List<Review>>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(reviews)
                .build();

    }

}
