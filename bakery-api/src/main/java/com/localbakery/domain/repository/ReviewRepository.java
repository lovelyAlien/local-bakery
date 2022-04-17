package com.localbakery.domain.repository;

import com.localbakery.domain.entity.Review;
import com.localbakery.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByStoreId(Long storeId);

    List<Review> findAllByReviewerId(Long reviewerId);

}
