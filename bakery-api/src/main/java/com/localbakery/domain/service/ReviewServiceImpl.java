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
    public ReviewResponseVo write(List<MultipartFile> files, UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo) {
        //S3로 이미지 링크 리스트 가져오기
        List<String> imageUrls = files.stream()
                .map(file -> s3UploadService.uploadFileToS3(file).get("imageUrl"))
                .collect(Collectors.toList());

        //리뷰로 스토어 평점 갱신
        Store store = storeRepository.findById(reviewRequestVo.getStoreId()).get();
        store.rating(reviewRequestVo.getRating());

        //리뷰 저장
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

        //리뷰 이미지 리스트 테이블에 저장
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
    @Transactional
    public Long modify(Long reviewId, ReviewRequestVo reviewRequestVo) {

        Store store = storeRepository.findById(reviewRequestVo.getStoreId()).get();

        Review review = reviewRepository.findById(reviewId).get();

        float before_rating = review.getRating();
        float after_rating = reviewRequestVo.getRating();

        store.updateRating(before_rating, after_rating);

        review.update(reviewRequestVo);

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

        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);
        List<ReviewResponseVo> reviewResponseVos=new ArrayList<>();
        for(Review review: reviews){
            List<String> imageUrls= review.getImages().stream()
                    .map(image->image.getImageUrl())
                    .collect(Collectors.toList());
            reviewResponseVos.add(new ReviewResponseVo(review, imageUrls));
        }
        return reviewResponseVos;
    }

    @Override
    public ReviewResponseVo findOne(Long reviewId) {

        // TODO: 2022/04/22 images 필드를 가져오지 못함. 
        Review review = reviewRepository.findById(reviewId).get();
        List<String> imageUrls= review.getImages().stream()
                .map(image-> image.getImageUrl())
                .collect(Collectors.toList());
        return new ReviewResponseVo(review, imageUrls);

    }

    @Override
    public List<Review> findUserReviews(UserPrincipal userPrincipal) {


        return reviewRepository.findAllByReviewerId(userPrincipal.getId());
    }


}
