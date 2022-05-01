package com.localbakery.domain.repository;

import com.localbakery.domain.entity.Review;
import com.localbakery.domain.entity.Store;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT DISTINCT r from Review r join fetch r.images")
    Slice<Review> findAllByStoreId(Long storeId, PageRequest pageRequest);

    List<Review> findAllByReviewerId(Long reviewerId);

}
