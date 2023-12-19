package com.donggukthon.Showman.dto.comment.request;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {

    private Long commentId;
    private String content;
}
