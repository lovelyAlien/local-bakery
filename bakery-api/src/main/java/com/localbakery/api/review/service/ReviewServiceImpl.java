package com.localbakery.api.review.service;


import com.localbakery.account.service.S3UploadService;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.api.review.entity.Review;
import com.localbakery.api.review.entity.ReviewImage;
import com.localbakery.dataprovider.entity.Store;
import com.localbakery.api.review.model.ReviewRequestVo;
import com.localbakery.api.review.model.ReviewResponseVo;
import com.localbakery.api.review.repository.ReviewImageRepository;
import com.localbakery.api.review.repository.ReviewRepository;
import com.localbakery.dataprovider.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
  public List<ReviewResponseVo> findAll(Long storeId, int page, int size) {
    final int path = Math.max(page - 1, 0);
    final PageRequest pageRequest = PageRequest.of(page, size);

    //리뷰 목록 조회
    Slice<Review> reviews = reviewRepository.findAllByStoreId(storeId, pageRequest);
    List<ReviewResponseVo> reviewResponseVos = Optional.ofNullable(reviews)
      .map(list -> list.stream()
        .map(review -> ReviewResponseVo.of(review))
        .collect(Collectors.toList()))
      .orElseGet(() -> null);

    return reviewResponseVos;
  }

  @Override
  public ReviewResponseVo findOne(Long reviewId) {


    Review review = reviewRepository.findById(reviewId).get();
    return ReviewResponseVo.of(review);

  }

  @Override
  public List<Review> findUserReviews(UserPrincipal userPrincipal) {


    return reviewRepository.findAllByReviewerId(userPrincipal.getId());
  }


}
