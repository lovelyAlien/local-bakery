package com.localbakery.domain.repository;

import com.localbakery.domain.entity.Store;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT * " +
            "FROM stores s " +
            "ORDER BY ST_Distance_Sphere(s.location, :location) ASC"
            , nativeQuery = true
    )
    Slice<Store> findAllByLocationIsNear(@Param("location") Point location, Pageable pageable);
}
