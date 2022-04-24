package com.localbakery.account.dto;

import lombok.Builder;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
@Builder
public class AccountDTO {
    private Long userId;

    private String userEmail;

    private String profileImageUrl;

    private Point address;
}
