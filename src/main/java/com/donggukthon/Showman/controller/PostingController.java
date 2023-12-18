package com.donggukthon.Showman.controller;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.posting.request.PostingDescriptionRequest;
import com.donggukthon.Showman.dto.posting.request.PostingLocationRequest;
import com.donggukthon.Showman.dto.posting.response.PostingDescriptionResponse;
import com.donggukthon.Showman.dto.posting.response.PostingImageResponse;
import com.donggukthon.Showman.dto.posting.response.PostingLocationResponse;
import com.donggukthon.Showman.service.posting.PostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/posting/image")
    public CommonResponse<PostingImageResponse> postingImage(@RequestPart MultipartFile multipartFile) throws IOException {
        return CommonResponse.success(postingService.postingImage(multipartFile));
    }

    @PostMapping("/posting/description")
    public CommonResponse<PostingDescriptionResponse> postingDescription(@RequestBody PostingDescriptionRequest postingDescriptionRequest){
        return CommonResponse.success(postingService.postingDescription(postingDescriptionRequest));
    }

    @PostMapping("/posting/location")
    public CommonResponse<PostingLocationResponse> postingLocation(@RequestBody PostingLocationRequest postingLocationRequest){
        return CommonResponse.success(postingService.postingLocation(postingLocationRequest));
    }

}
