package com.localbakery.query.service;

import com.localbakery.domain.entity.Store;
import com.localbakery.query.model.StoreBo;
import com.localbakery.domain.repository.StoreRepository;
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
    public Slice<StoreBo> findAllByLocationNear(Double latitude, Double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));
        Pageable pageable = Pageable.ofSize(10);
        Slice<Store> storeSlice = storeRepository.findAllByLocationNear(point, pageable);
        List<StoreBo> storeBoList = CollectionUtils.emptyIfNull(storeSlice.getContent()).stream()
                .filter(Objects::nonNull)
                .map(StoreBo.FROM)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new SliceImpl<>(storeBoList, storeSlice.getPageable(), storeSlice.hasNext());
    }
}
