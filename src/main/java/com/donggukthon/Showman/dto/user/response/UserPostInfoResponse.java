package com.donggukthon.Showman.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserPostInfoResponse {

    private Long postingId;
    private String snowmanName; /* title */
    private String snowmanImageUrl;
    private LocalDateTime createAt; // 눈사람 등록 일시
    private String address;
    private Long heartCnt;
    private Long commentCnt;
    private Long userId;
    private String nickname;

    public static UserPostInfoResponse of(Long postingId, String snowmanName, String snowmanImageUrl, LocalDateTime createAt, String address,
                                          Long heartCnt, Long commentCnt, Long userId, String nickname){
        return UserPostInfoResponse.builder().postingId(postingId).snowmanName(snowmanName).snowmanImageUrl(snowmanImageUrl).createAt(createAt).address(address)
                .heartCnt(heartCnt).commentCnt(commentCnt).userId(userId).nickname(nickname).build();
    }
}
