package com.localbakery.account.controller;

import com.localbakery.messages.ResponseContainer;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.api.review.entity.Review;
import com.localbakery.api.review.service.ReviewService;
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
