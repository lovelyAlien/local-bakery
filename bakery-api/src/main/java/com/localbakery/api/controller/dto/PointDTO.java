package com.localbakery.api.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointDTO {
    private Double leftBottomLongitude;

    private Double leftBottomLatitude;

    private Double rightTopLongitude;

    private Double rightTopLatitude;
}
