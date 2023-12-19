package com.donggukthon.Showman.controller;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.heart.request.HeartLikeRequest;
import com.donggukthon.Showman.dto.heart.request.HeartUnLikeRequest;
import com.donggukthon.Showman.dto.heart.response.HeartResponse;
import com.donggukthon.Showman.service.heart.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HeartController {

    private final HeartService heartService;

    // 눈사람 게시글 좋아요
    @PostMapping("/heart")
    public CommonResponse<HeartResponse> like(@RequestBody HeartLikeRequest heartLikeRequest){
        return CommonResponse.success(heartService.like(heartLikeRequest));
    }

    // 눈사람 게시글 좋아요 취소
    @DeleteMapping("/heart")
    public CommonResponse<HeartResponse> unLike(@RequestBody HeartUnLikeRequest heartUnLikeRequest){
        return CommonResponse.success(heartService.unLike(heartUnLikeRequest));
    }
}
