package com.localbakery.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coordinate {
    private final Double longitude;
    private final Double latitude;
}
