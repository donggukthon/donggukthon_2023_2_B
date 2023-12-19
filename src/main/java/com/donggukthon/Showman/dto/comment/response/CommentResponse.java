package com.donggukthon.Showman.dto.comment.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long commentId;
    private String content;
    private Long userId;
    private String nickname;
    private LocalDateTime createdAt;

    public static CommentResponse of(Long commentId, String content, Long userId, String nickname, LocalDateTime createdAt) {
        return CommentResponse.builder()
                .commentId(commentId)
                .content(content)
                .userId(userId)
                .nickname(nickname)
                .createdAt(createdAt)
                .build();
    }
}
