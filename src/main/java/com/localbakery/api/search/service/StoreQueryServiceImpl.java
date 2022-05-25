package com.localbakery.api.search.service;

import com.localbakery.dataprovider.entity.Store;
import com.localbakery.dataprovider.repository.StoreRepository;
import com.localbakery.api.search.model.StoreBo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
    public Slice<StoreBo> findAllByLocationNear(Double leftBottomLongitude, Double leftBottomLatitude, Double rightTopLongitude, Double rightTopLatitude) {
        Pageable pageable = Pageable.ofSize(10);

        Slice<Store> storeSlice = storeRepository.findAllByLocationIsNear(leftBottomLongitude, leftBottomLatitude, rightTopLongitude, rightTopLatitude, pageable);

        List<StoreBo> storeBoList = CollectionUtils.emptyIfNull(storeSlice.getContent()).stream()
                .filter(Objects::nonNull)
                .map(StoreBo.FROM)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new SliceImpl<>(storeBoList, storeSlice.getPageable(), storeSlice.hasNext());
    }
}
