package com.donggukthon.Showman.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.donggukthon.Showman.service.user.UserService;
import com.donggukthon.Showman.dto.user.response.UserProfileResponse;
import com.donggukthon.Showman.dto.user.request.UpdateUserRequest;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

//    @GetMapping(value = "/me", produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse<UserProfileResponse>> getMyProfile(
//            @AuthenticationPrincipal JwtAuthenticationRecord user
//    ) {
//        UserProfileResponse response = userService.getUserProfile(user.id());
//
//        return ResponseEntity.ok().body(new ApiResponse<>(response));
//    }
//
//    @PutMapping(value = "/me", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse<UserProfileResponse>> updateMyProfile(
//            @RequestBody @Valid UpdateUserRequest updateUserRequest,
//            @AuthenticationPrincipal JwtAuthenticationRecord user
//    ) {
//        UserProfileResponse response = userService.updateUserProfile(updateUserRequest, user.id());
//
//        return ResponseEntity.ok().body(new ApiResponse<>(response));
//    }
//
//    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(
//            @PathVariable Long userId
//    ) {
//        UserProfileResponse response = userService.getUserProfile(userId);
//
//        return ResponseEntity.ok().body(new ApiResponse<>(response));
//    }
}

