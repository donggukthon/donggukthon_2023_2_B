package com.donggukthon.Showman.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponse {
    private Long userId;
    private String nickname;
    private Long snowmanCnt;

    public static UserInfoResponse of(Long userId, String nickname, Long snowmanCnt){
        return UserInfoResponse.builder().userId(userId).nickname(nickname).snowmanCnt(snowmanCnt).build();
    }
}
