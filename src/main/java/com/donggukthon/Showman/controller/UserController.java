package com.donggukthon.Showman.controller;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.user.request.ModifiedUserInfoRequest;
import com.donggukthon.Showman.dto.user.response.CommentInfoResponse;
import com.donggukthon.Showman.dto.user.response.ModifiedUserInfoResponse;
import com.donggukthon.Showman.dto.user.response.PostInfoResponse;
import com.donggukthon.Showman.dto.user.response.UserInfoResponse;
import org.springframework.web.bind.annotation.*;
import com.donggukthon.Showman.service.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    /* 유저 정보 조회 */
    @GetMapping("/user")
    public CommonResponse<UserInfoResponse> getUserInfo() {
        return CommonResponse.success(userService.getUserInfo());
    }

    /* 닉네임 수정 */
    @PostMapping("/user")
    public CommonResponse<ModifiedUserInfoResponse> modifyUserInfo(ModifiedUserInfoRequest modifiedUserInfoRequest) {
        return CommonResponse.success(userService.modifyUserInfo(modifiedUserInfoRequest));
    }

    /* 내 눈사람 보기 */
    @GetMapping("/user/snowman")
    public CommonResponse<List<PostInfoResponse>> getPostInfo() {
        return CommonResponse.success(userService.getPostInfo());}

    /* 내 댓글 보기 */
    @GetMapping("/user/comment")
    public CommonResponse<List<CommentInfoResponse>> getCommentInfo(){
        return CommonResponse.success(userService.getCommentInfo());
    }


    /* 좋아요한 게시물 보기 */
    @GetMapping("/user/heart")
    public CommonResponse<List<PostInfoResponse>> getHeartInfo(){
        return CommonResponse.success(userService.getHeartInfo());
    }

    /* 저장한 게시글 보기 */
    @GetMapping("/user/scrap")
    public CommonResponse<List<PostInfoResponse>> getScrapInfo(){
        return CommonResponse.success(userService.getScrapInfo());
    }
}



