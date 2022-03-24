package com.localbakery.domain.service;


import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.entity.Store;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.repository.ReviewRepository;
import com.localbakery.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements  ReviewService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    @Override
    public Long write(UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo) {

        Store store=storeRepository.findById(reviewRequestVo.getStoreId()).get();

        Review  review= reviewRepository.save(
                Review.builder()
                .store(store)
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
