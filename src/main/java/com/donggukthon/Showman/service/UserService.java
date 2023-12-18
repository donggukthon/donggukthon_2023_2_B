package com.donggukthon.Showman.service;


import com.donggukthon.Showman.dto.UpdateUserRequest;
import com.donggukthon.Showman.dto.UserProfileResponse;
import com.donggukthon.Showman.global.auth.dto.AuthUserInfo;
import com.donggukthon.Showman.global.auth.dto.OAuthUserInfo;

public interface UserService {
    /* 회원 등록 */
    AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo);

    /* 회원 프로필 조회 */
    UserProfileResponse getUserProfile(Long userId);

    /* 회원 프로필 수정 */
    UserProfileResponse updateUserProfile(UpdateUserRequest updateUserRequest, Long userId);

}
