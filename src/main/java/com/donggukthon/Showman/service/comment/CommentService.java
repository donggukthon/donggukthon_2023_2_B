package com.donggukthon.Showman.service.comment;

import com.donggukthon.Showman.dto.comment.request.DeleteCommentRequest;
import com.donggukthon.Showman.dto.comment.request.UpdateCommentRequest;
import com.donggukthon.Showman.dto.comment.response.DeleteCommentResponse;
import com.donggukthon.Showman.dto.comment.response.WriteCommentRequest;
import com.donggukthon.Showman.dto.comment.response.WriteCommentResponse;

public interface CommentService {
    WriteCommentResponse writeComment(Long postingId, WriteCommentRequest writeCommentRequest);

    WriteCommentResponse updateComment(UpdateCommentRequest updateCommentRequest);

    DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest);
}
