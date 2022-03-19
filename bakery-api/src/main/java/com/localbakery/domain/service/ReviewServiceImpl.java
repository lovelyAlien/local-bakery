package com.localbakery.domain.service;


import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements  ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public Long write(UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo) {
        Review  review= reviewRepository.save(
                Review.builder()
                .storeId(reviewRequestVo.getStoreId())
                .reviewerId(userPrincipal.getId())
                .reviewerEmail(userPrincipal.getEmail())
                .contents(reviewRequestVo.getContents())
                .rating(reviewRequestVo.getRating())
                .build());

        return review.getReviewId();
    }

    @Override
    public Long modify(Long reviewId, String contents){

        Review review=reviewRepository.findById(reviewId).get();

        review.setContents(contents);

        reviewRepository.save(review);

        return review.getReviewId();

    }

    @Override
    public void delete(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).get();
        reviewRepository.delete(review);
    }


}
