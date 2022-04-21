package com.localbakery.domain.service;


import com.amazonaws.services.s3.AmazonS3;
import com.localbakery.account.service.S3UploadService;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.entity.ReviewImage;
import com.localbakery.domain.entity.Store;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.repository.ReviewImageRepository;
import com.localbakery.domain.repository.ReviewRepository;
import com.localbakery.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final S3UploadService s3UploadService;

    @Override
//    @Transactional
    public ReviewResponseVo write(List<MultipartFile> files, UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo) {
        List<String> imageUrls = files.stream()
                .map(file -> s3UploadService.uploadFileToS3(file).get("imageUrl"))
                .collect(Collectors.toList());


        Store store = storeRepository.findById(reviewRequestVo.getStoreId()).get();
        store.rating(reviewRequestVo.getRating());

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

        List<ReviewImage> reviewImages = imageUrls.stream()
                .map(url -> reviewImageRepository.save(
                        ReviewImage.builder()
                                .imageUrl(url)
                                .review(review)
                                .build())
                )
                .collect(Collectors.toList());

        review.setImages(reviewImages);

        return new ReviewResponseVo(review, imageUrls);

    }

    @Override
    public Long modify(Long reviewId, ReviewRequestVo reviewRequestVo) {

        Store store = storeRepository.findById(reviewRequestVo.getStoreId()).get();

        Review review = reviewRepository.findById(reviewId).get();

        float before_rating = review.getRating();
        float after_rating = reviewRequestVo.getRating();

        store.updateRating(before_rating, after_rating);

        review.update(reviewRequestVo);

        reviewRepository.save(review);

        return review.getReviewId();

    }

    @Override
    public Long delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();

        Store store = storeRepository.findById(review.getStoreId()).get();
        store.deleteRating(review.getRating());

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

        // TODO: 2022/04/22 images 필드를 가져오지 못함. 
        Review review = reviewRepository.findById(reviewId).get();
        return new ReviewResponseVo(review);

    }


}
