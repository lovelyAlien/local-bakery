package com.localbakery.api.review.repository;

import com.localbakery.api.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

}
