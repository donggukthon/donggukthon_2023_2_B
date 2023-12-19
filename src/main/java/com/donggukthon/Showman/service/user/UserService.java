package com.donggukthon.Showman.service.user;

import com.donggukthon.Showman.dto.user.request.ModifiedUserInfoRequest;
import com.donggukthon.Showman.dto.user.response.CommentInfoResponse;
import com.donggukthon.Showman.dto.user.response.ModifiedUserInfoResponse;
import com.donggukthon.Showman.dto.user.response.PostInfoResponse;
import com.donggukthon.Showman.dto.user.response.UserInfoResponse;
import com.donggukthon.Showman.entity.User;


import javax.naming.AuthenticationException;
import java.util.List;

public interface UserService {

    Long getCurrentUserId() throws AuthenticationException;

    // 현재 유저 조회
    User getCurrentUser();

    // 유저 정보 조회
    UserInfoResponse getUserInfo();

    // 닉네임 수정
    ModifiedUserInfoResponse modifyUserInfo(ModifiedUserInfoRequest modifiedUserInfoRequest);

    // 눈사람 조회
    List<PostInfoResponse> getPostInfo();

    // 댓글 조회
    List<CommentInfoResponse> getCommentInfo();

    // 좋아요한 게시글 조회
    List<PostInfoResponse> getHeartInfo();

    // 저장한 게시글 조회
    List<PostInfoResponse> getScrapInfo();
}
