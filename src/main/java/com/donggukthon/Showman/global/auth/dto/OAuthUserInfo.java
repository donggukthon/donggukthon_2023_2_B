package com.donggukthon.Showman.global.auth.dto;

import lombok.Builder;

@Builder
public record OAuthUserInfo (
        String nickname,
        String oauthId
){

}
