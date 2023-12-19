package com.donggukthon.Showman.dto.comment.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WriteCommentResponse {

    private Long commentId;
    private String content;

    public static WriteCommentResponse of(Long commentId, String content) {
        return WriteCommentResponse.builder()
                .commentId(commentId)
                .content(content)
                .build();
    }
}
