package com.localbakery.repository;

import com.localbakery.domain.Store;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Slice<Store> findAllByLocationNear(Point location, Pageable pageable);
}
