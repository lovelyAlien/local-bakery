package com.localbakery.domain.service;


import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
