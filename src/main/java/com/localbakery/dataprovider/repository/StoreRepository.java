package com.localbakery.dataprovider.repository;

import com.localbakery.dataprovider.entity.Store;
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
            "WHERE ST_X(location) > :leftBottomLongitude and ST_X(location) < :rightTopLongitude and ST_Y(location) > :leftBottomLatitude and ST_Y(location) < :rightTopLatitude"
            , nativeQuery = true
    )
    Slice<Store> findAllByLocationIsNear(@Param("leftBottomLongitude") Double leftBottomLongitude, @Param("leftBottomLatitude") Double leftBottomLatitude, @Param("rightTopLongitude") Double rightTopLongitude, @Param("rightTopLatitude") Double rightTopLatitude, Pageable pageable);
}
