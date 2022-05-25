package com.localbakery.api.review.service;

import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.api.review.entity.Review;
import com.localbakery.api.review.model.ReviewRequestVo;
import com.localbakery.api.review.model.ReviewResponseVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    ReviewResponseVo write(List<MultipartFile> files, UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo);

    Long modify(Long reviewId, ReviewRequestVo reviewRequestVo);

    Long delete(Long reviewId);

    List<ReviewResponseVo> findAll(Long storeId, int page, int size);

    ReviewResponseVo findOne(Long reviewId);

    List<Review> findUserReviews(UserPrincipal userPrincipal);
}
