package com.donggukthon.Showman.dto.user.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter

public class ModifiedUserInfoResponse {
    private Long userId;
    private String nickname;

    public static ModifiedUserInfoResponse of(Long userId, String nickname){
        return ModifiedUserInfoResponse.builder().userId(userId).nickname(nickname).build();
    }
}
