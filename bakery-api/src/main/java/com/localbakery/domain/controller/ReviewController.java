package com.localbakery.domain.controller;


import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.api.search.model.SearchRequestVo;
import com.localbakery.api.search.model.SearchResponseVo;
import com.localbakery.api.search.service.SearchService;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.domain.entity.Review;
import com.localbakery.domain.model.ReviewRequestVo;
import com.localbakery.domain.model.ReviewResponseVo;
import com.localbakery.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @RequestMapping(value = "reviews/review", method = RequestMethod.POST)
  public ResponseContainer<ReviewResponseVo> write(
    @RequestPart(value = "files", required = false) List<MultipartFile> files,
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestPart(value = "reviewRequestVo", required = false) ReviewRequestVo reviewRequestVo) {


    ReviewResponseVo reviewResponseVo = reviewService.write(files, userPrincipal, reviewRequestVo);
    return ResponseContainer.<ReviewResponseVo>builder()
      .rMessage("OK")
      .rCode("200")
      .rData(reviewResponseVo)
      .build();

  }


  @RequestMapping(value = "reviews/{id}", method = RequestMethod.PUT)
  public ResponseContainer<Long> modify(
    @PathVariable Long id,
    @RequestBody ReviewRequestVo reviewRequestVo) {

    Long reviewId = reviewService.modify(id, reviewRequestVo);
    return ResponseContainer.<Long>builder()
      .rMessage("OK")
      .rCode("200")
      .rData(reviewId)
      .build();
  }

  @RequestMapping(value = "reviews/{id}", method = RequestMethod.DELETE)
  public ResponseContainer<Long> delete(@PathVariable Long id) {
    Long reviewId = reviewService.delete(id);

    return ResponseContainer.<Long>builder()
      .rMessage("OK")
      .rCode("200")
      .rData(reviewId)
      .build();
  }

  @RequestMapping(value = "stores/{id}/reviews", method = RequestMethod.GET)
  public ResponseContainer<List<ReviewResponseVo>> findAll(
    @PathVariable Long id,
    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

    List<ReviewResponseVo> reviews = reviewService.findAll(id, page, size);

    return ResponseContainer.<List<ReviewResponseVo>>builder()
      .rMessage("OK")
      .rCode("200")
      .rData(reviews)
      .build();
  }

  @RequestMapping(value = "stores/{storeId}/reviews/{id}", method = RequestMethod.GET)
  public ResponseContainer<ReviewResponseVo> findOne(@PathVariable Long id, @PathVariable String storeId) {

    ReviewResponseVo review = reviewService.findOne(id);

    return ResponseContainer.<ReviewResponseVo>builder()
      .rMessage("OK")
      .rCode("200")
      .rData(review)
      .build();
  }


}
