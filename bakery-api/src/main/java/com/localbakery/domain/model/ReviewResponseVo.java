package com.localbakery.domain.model;

import com.localbakery.domain.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
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


    private float rating;


    private LocalDateTime modifiedAt;


    private LocalDateTime createdAt;


    public ReviewResponseVo(Review review){
        reviewId= review.getReviewId();
        storeId= review.getStoreId();
        reviewerId= review.getReviewerId();
        reviewerEmail= review.getReviewerEmail();
        contents=review.getContents();
        specials= review.getSpecials().split(",");
        recommends= review.getRecommends().split(",");
        rating=review.getRating();
        modifiedAt=review.getModifiedAt();
        createdAt=review.getCreatedAt();

    }

}
