package com.localbakery.domain.service;

import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;

public interface ReviewService {
    Long write(UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo);

    Long modify(Long reviewId, String contents);

    void delete(Long reviewId);
}
