package com.localbakery.domain.service;

import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;

import java.util.List;

public interface ReviewService {
    Long write(UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo);

    Long modify(Long reviewId, String contents);

    void delete(Long reviewId);

    List<Review> findAll(Long storeId);

    Review findOne(Long reviewId);
}
