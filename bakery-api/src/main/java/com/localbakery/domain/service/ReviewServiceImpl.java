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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Long write(UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo) {

        Store store = storeRepository.findById(reviewRequestVo.getStoreId()).get();
        store.updateRating(reviewRequestVo.getRating());

        Review review = reviewRepository.save(
                Review.builder()
                        .storeId(reviewRequestVo.getStoreId())
                        .reviewerId(userPrincipal.getId())
                        .reviewerEmail(userPrincipal.getEmail())
                        .contents(reviewRequestVo.getContents())
                        .rating(reviewRequestVo.getRating())
                        .specials(reviewRequestVo.getSpecials())
                        .recommends(reviewRequestVo.getRecommends())
                        .build());

        return review.getReviewId();
    }

    @Override
    public Long modify(Long reviewId, ReviewRequestVo reviewRequestVo) {

        Review review = reviewRepository.findById(reviewId).get();

        review.update(reviewRequestVo);

        reviewRepository.save(review);

        return review.getReviewId();

    }

    @Override
    public Long delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        reviewRepository.delete(review);
        return reviewId;
    }

    @Override
    public List<ReviewResponseVo> findAll(Long storeId) {

//        return reviewRepository.findAllByStoreId(storeId);
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);
        return reviews.stream()
                .map(review -> new ReviewResponseVo(review))
                .collect(Collectors.toList());


    }

    @Override
    public ReviewResponseVo findOne(Long reviewId) {

        Review review= reviewRepository.findById(reviewId).get();
        return new ReviewResponseVo(review);

    }


}
