package com.localbakery.query.model;

import com.localbakery.domain.Store;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.locationtech.jts.geom.Point;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
@Getter
public class StoreBo {
    private Long storeId;
    private List<MenuBo> menus;
    private String name;
    private String phoneNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private Point location;

    public boolean isOpen() {
        if (startTime == null || null == endTime) {
            return false;
        }
        LocalTime now = LocalTime.now();
        return now.isAfter(startTime) && now.isBefore(endTime);
    }

    public static final Function<Store, StoreBo> FROM = (Store source) -> {
        if (null == source) {
            return null;
        }
        return StoreBo.builder()
                .storeId(source.getStoreId())
                .name(source.getName())
                .phoneNumber(source.getPhoneNumber())
                .startTime(source.getStartTime())
                .endTime(source.getEndTime())
                .location(source.getLocation())
                .menus(CollectionUtils.emptyIfNull(source.getMenus())
                        .stream()
                        .map(MenuBo.FROM)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
                )
                .build();

    };
}
