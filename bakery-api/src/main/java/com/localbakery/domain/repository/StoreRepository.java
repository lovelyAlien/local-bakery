package com.localbakery.domain.repository;

import com.localbakery.domain.entity.Store;
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
            "WHERE ST_X(location) > :longitude and ST_X(location) < :longitude2 and ST_Y(location) > :latitude and ST_Y(location) < :latitude2"
            , nativeQuery = true
    )
    Slice<Store> findAllByLocationIsNear(@Param("longitude") Double longitude, @Param("latitude") Double latitude, @Param("longitude2") Double longitude2, @Param("latitude2") Double latitude2, Pageable pageable);
}
