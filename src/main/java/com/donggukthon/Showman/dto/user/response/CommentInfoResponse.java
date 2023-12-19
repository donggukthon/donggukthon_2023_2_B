package com.donggukthon.Showman.dto.user.response;

/*
 * response : positng id , snowman title,
 * snowman image url, comment create at,
 * comment content, comment id
 */

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentInfoResponse {
    private Long commentId;
    private LocalDateTime commentCreateAt; // 댓글 작성 일시
    private String content;

    public static CommentInfoResponse of(
            Long postingId, String snowmanName, String snowmanImageUrl, Long commentId, LocalDateTime commentCreateAt, String Content

    ){
        return CommentInfoResponse.builder()
                .commentId(commentId)
                .commentCreateAt(commentCreateAt)
                .content(Content)
                .build();
    }

}
