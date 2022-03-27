package com.localbakery.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;

//    @ManyToOne
//    @JoinColumn(name="storeId")
//    private Store store;

    @Column(name= "storeId")
    private Long storeId;

    @Column(name = "reviewerId")
    private Long reviewerId;

    @Column(name = "reviewerEmail")
    private String reviewerEmail;

    @Column(name = "contents")
    private String contents;

    @Column(name = "specials")
    private String specials;

    @Column(name = "recommands")
    private String recommands;

    @Column(name= "rating")
    private float rating;

//    @Column(name = "images")
//    private Long images;

    @CreatedDate
    @Column(name = "modifiedAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @LastModifiedDate
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;


    public void setContents(String contents) {
        this.contents = contents;
    }
}
