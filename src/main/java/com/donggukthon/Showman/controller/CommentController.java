package com.donggukthon.Showman.controller;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.comment.request.DeleteCommentRequest;
import com.donggukthon.Showman.dto.comment.request.UpdateCommentRequest;
import com.donggukthon.Showman.dto.comment.response.DeleteCommentResponse;
import com.donggukthon.Showman.dto.comment.response.WriteCommentRequest;
import com.donggukthon.Showman.dto.comment.response.WriteCommentResponse;
import com.donggukthon.Showman.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // 눈사람 게시글 댓글 작성
    @PostMapping("/posting/{postingId}/comment")
    public CommonResponse<WriteCommentResponse> writeComment(@PathVariable Long postingId, @RequestBody WriteCommentRequest writeCommentRequest){
        return CommonResponse.success(commentService.writeComment(postingId,writeCommentRequest));
    }

    // 눈사람 게시글 댓글 수정
    @PatchMapping("/posting/comment")
    public CommonResponse<WriteCommentResponse> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){
        return CommonResponse.success(commentService.updateComment(updateCommentRequest));
    }

    // 눈사람 게시글 댓글 삭제
    @DeleteMapping("/posting/comment")
    public CommonResponse<DeleteCommentResponse> deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest){
        return CommonResponse.success(commentService.deleteComment(deleteCommentRequest));
    }
}
