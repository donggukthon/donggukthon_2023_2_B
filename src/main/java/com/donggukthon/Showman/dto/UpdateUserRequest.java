package com.donggukthon.Showman.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank String nickName
) {

}