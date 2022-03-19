package com.localbakery.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;

    @Column(name = "storeId")
    private Long storeId;

    @Column(name = "reviewerId")
    private Long reviewerId;

    @Column(name = "contents")
    private String contents;

    @Column(name = "reviewerName")
    private String reviewerName;

//    @Column(name = "images")
//    private Long images;

    @Column(name = "modifiedAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
