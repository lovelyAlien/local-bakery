package com.localbakery.domain.model;

import com.localbakery.domain.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@SuperBuilder
@NoArgsConstructor
@Getter
public class ReviewResponseVo {

    private Long reviewId;


    private Long storeId;


    private Long reviewerId;


    private String reviewerEmail;


    private String contents;


    private String[] specials;


    private String[] recommends;

    private List<String> imageUrls;

    private float rating;


    private LocalDateTime modifiedAt;


    private LocalDateTime createdAt;




    public ReviewResponseVo(Review review, List<String> imageUrls){
        reviewId= review.getReviewId();
        storeId= review.getStoreId();
        reviewerId= review.getReviewerId();
        reviewerEmail= review.getReviewerEmail();
        contents=review.getContents();
        specials= review.getSpecials().split(",");
        recommends= review.getRecommends().split(",");
        this.imageUrls =imageUrls;
        rating=review.getRating();
        modifiedAt=review.getModifiedAt();
        createdAt=review.getCreatedAt();

    }

    public static ReviewResponseVo of(Review review){

        ReviewResponseVoBuilder<?, ?> reviewResponseVo= ReviewResponseVo.builder();
        reviewResponseVo.reviewId=review.getReviewId();
        reviewResponseVo.storeId= review.getStoreId();
        reviewResponseVo.reviewerId=review.getReviewerId();
        reviewResponseVo.reviewerEmail=review.getReviewerEmail();
        reviewResponseVo.contents=review.getContents();
        reviewResponseVo.specials=review.getSpecials().split(",");
        reviewResponseVo.recommends=review.getRecommends().split(",");

        reviewResponseVo.imageUrls=review.getImages().stream()
          .map(image->image.getImageUrl())
          .collect(Collectors.toList());

        reviewResponseVo.rating=review.getRating();
        reviewResponseVo.modifiedAt=review.getModifiedAt();
        reviewResponseVo.createdAt=review.getCreatedAt();

        return reviewResponseVo.build();
    }

}
