package com.donggukthon.Showman.dto.user.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank String nickName
) {

}