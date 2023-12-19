package com.donggukthon.Showman.dto.comment.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteCommentResponse {

    private Long commentId;

    public static DeleteCommentResponse of(Long commentId){
        return DeleteCommentResponse.builder()
                .commentId(commentId)
                .build();
    }
}
