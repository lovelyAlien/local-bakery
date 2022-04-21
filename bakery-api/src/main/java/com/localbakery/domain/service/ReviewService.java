package com.localbakery.domain.service;

import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    ReviewResponseVo write(List<MultipartFile> files, UserPrincipal userPrincipal, ReviewRequestVo reviewRequestVo);

    Long modify(Long reviewId, ReviewRequestVo reviewRequestVo);

    Long delete(Long reviewId);

    List<ReviewResponseVo> findAll(Long storeId);

    ReviewResponseVo findOne(Long reviewId);
}
