package com.localbakery.query.service;

import com.localbakery.domain.entity.Store;
import com.localbakery.domain.repository.StoreRepository;
import com.localbakery.query.model.StoreBo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;

    @Override
    public Slice<StoreBo> findAllByLocationNear(Double longitude, Double latitude, Double longitude2, Double latitude2) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        Point point2 = geometryFactory.createPoint(new Coordinate(longitude2, latitude2));
        Pageable pageable = Pageable.ofSize(10);

        if (point.getX() > point2.getX()) {
            Point temp = point;
            point = point2;
            point2 = temp;
        }

        Slice<Store> storeSlice = storeRepository.findAllByLocationIsNear(point.getX(), point.getY(), point2.getX(), point2.getY(), pageable);
        List<StoreBo> storeBoList = CollectionUtils.emptyIfNull(storeSlice.getContent()).stream()
                .filter(Objects::nonNull)
                .map(StoreBo.FROM)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new SliceImpl<>(storeBoList, storeSlice.getPageable(), storeSlice.hasNext());
    }
}
