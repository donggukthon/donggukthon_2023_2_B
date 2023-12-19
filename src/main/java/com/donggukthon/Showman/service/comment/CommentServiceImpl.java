package com.donggukthon.Showman.service.comment;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.dto.comment.request.DeleteCommentRequest;
import com.donggukthon.Showman.dto.comment.request.UpdateCommentRequest;
import com.donggukthon.Showman.dto.comment.response.DeleteCommentResponse;
import com.donggukthon.Showman.dto.comment.response.WriteCommentRequest;
import com.donggukthon.Showman.dto.comment.response.WriteCommentResponse;
import com.donggukthon.Showman.entity.Comment;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.CommentRepository;
import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;
    private final UserServiceImpl userServiceImpl;

    @Override
    @Transactional
    public WriteCommentResponse writeComment(Long postingId, WriteCommentRequest writeCommentRequest) {
        User user = userServiceImpl.getCurrentUser();

        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));

        Comment comment = Comment.builder()
                .posting(posting)
                .content(writeCommentRequest.getContent())
                .user(user)
                .build();

        commentRepository.save(comment);

        return WriteCommentResponse.of(comment.getCommentId(), comment.getContent());
    }

    @Override
    @Transactional
    public WriteCommentResponse updateComment(UpdateCommentRequest updateCommentRequest) {

        Comment comment = commentRepository.findById(updateCommentRequest.getCommentId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_COMMENT));

        comment.update(updateCommentRequest.getContent());

        return WriteCommentResponse.of(comment.getCommentId(), comment.getContent());
    }

    @Override
    @Transactional
    public DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest) {
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_COMMENT));

        commentRepository.delete(comment);

        return DeleteCommentResponse.of(comment.getCommentId());
    }
}
