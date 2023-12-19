package com.donggukthon.Showman.service.user;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.config.SecurityUtils;
import com.donggukthon.Showman.dto.user.request.ModifiedUserInfoRequest;
import com.donggukthon.Showman.dto.user.response.CommentInfoResponse;
import com.donggukthon.Showman.dto.user.response.ModifiedUserInfoResponse;
import com.donggukthon.Showman.dto.user.response.PostInfoResponse;
import com.donggukthon.Showman.dto.user.response.UserInfoResponse;
import com.donggukthon.Showman.entity.*;
import com.donggukthon.Showman.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.repository.CommentRepository;
import com.donggukthon.Showman.repository.HeartRepository;

import lombok.RequiredArgsConstructor;
import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final ScrapRepository scrapRepository;


    public Long getCurrentUserId() throws AuthenticationException {
        return SecurityUtils.getCurrentUserId();
    }

    // 현재 사용자 조회
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        try {
            return userRepository
                    .findById(SecurityUtils.getCurrentUserId()).get();
        } catch (Exception e) {
            throw new CustomException(Result.NOT_FOUND_USER);
        }
    }

    // 유저 정보 가져오기
    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo() {
        User user = getCurrentUser();

        Long snowmanCnt = postingRepository.countByUser(user);

        return UserInfoResponse.of(
                user.getUserId(),
                user.getNickname(),
                snowmanCnt);
    }

    // 유저 닉네임 수정하기
    @Override
    @Transactional
    public ModifiedUserInfoResponse modifyUserInfo(ModifiedUserInfoRequest modifiedUserInfoRequest) {
        User user = getCurrentUser();
        user.setNickname(modifiedUserInfoRequest.getNickname());

        return ModifiedUserInfoResponse.of(
                user.getUserId(),
                user.getNickname());

    }

    // 눈사람(snowman post) 조회하기
    @Override
    @Transactional(readOnly = true)
    public List<PostInfoResponse> getPostInfo() {
        User user = getCurrentUser();
        List<Posting> postings = postingRepository.findByUser(user);

        ArrayList<PostInfoResponse> postInfoResponses = new ArrayList<>();

        for (Posting posting : postings) {
            Long heartCnt = heartRepository.countByHeart(posting);
            Long commentCnt = commentRepository.countByComment(posting);

            PostInfoResponse postInfoResponse = PostInfoResponse.of(
                    posting.getPostingId(),
                    posting.getSnowmanName(),
                    posting.getSnowmanImageUrl(),
                    posting.getCreatedAt(),
                    posting.getAddress(),
                    heartCnt,
                    commentCnt);

            postInfoResponses.add(postInfoResponse);
        }

        return postInfoResponses;
    }

    // 내가 작성한 댓글 조회하기
    @Override
    @Transactional(readOnly = true)
    public List<CommentInfoResponse> getCommentInfo() {
        User user = getCurrentUser();
        List<Comment> comments = commentRepository.findByUser(user);

        ArrayList<CommentInfoResponse> commentInfoResponses = new ArrayList<>();

        for (Comment comment : comments) {

            // 현재 comment가 달린 post에서 필요한 정보 가져오기
            Posting relatedPosting = comment.getPosting();

            Long postingId = relatedPosting.getPostingId();
            String snowmanName = relatedPosting.getSnowmanName();
            String snowmanImageUrl = relatedPosting.getSnowmanImageUrl();

            CommentInfoResponse commentInfoResponse = CommentInfoResponse.of(
                    postingId,
                    snowmanName,
                    snowmanImageUrl,
                    comment.getCommentId(),
                    comment.getCreatedAt(),
                    comment.getContent()
            );
            commentInfoResponses.add(commentInfoResponse);
        }
        return commentInfoResponses;
    }

    // 좋아요 한 게시글 조회하기
    @Override
    @Transactional(readOnly = true)
    public List<PostInfoResponse> getHeartInfo() {
        User user = getCurrentUser();
        List<Heart> hearts = heartRepository.findByUser(user);

        ArrayList<PostInfoResponse> heartInfo = new ArrayList<>();

        for (Heart heart : hearts) {
            // 현재 유저의 좋아요(heart)에 연관된 Posting 객체 가져오기
            Posting posting = heart.getPosting();

            Long heartCnt = heartRepository.countByHeart(posting);
            Long commentCnt = commentRepository.countByComment(posting);

            PostInfoResponse postInfoResponse = PostInfoResponse.of(
                    posting.getPostingId(),
                    posting.getSnowmanName(),
                    posting.getSnowmanImageUrl(),
                    posting.getCreatedAt(),
                    posting.getAddress(),
                    heartCnt,
                    commentCnt
            );

            heartInfo.add(postInfoResponse);
        }

        return heartInfo;

    }

    // 저장한 게시글 조회하기
    @Override
    @Transactional(readOnly = true)
    public List<PostInfoResponse> getScrapInfo() {
        User user = getCurrentUser();
        List<Scrap> scraps = scrapRepository.findByUser(user);

        ArrayList<PostInfoResponse> ScrapInfo = new ArrayList<>();

        for (Scrap scrap : scraps) {
            // 현재 유저의 저장(scrap)에 연관된 Posting 객체 가져오기
            Posting posting = scrap.getPosting();

            Long heartCnt = heartRepository.countByHeart(posting);
            Long commentCnt = commentRepository.countByComment(posting);

            PostInfoResponse postInfoResponse = PostInfoResponse.of(
                    posting.getPostingId(),
                    posting.getSnowmanName(),
                    posting.getSnowmanImageUrl(),
                    posting.getCreatedAt(),
                    posting.getAddress(),
                    heartCnt,
                    commentCnt
            );

            ScrapInfo.add(postInfoResponse);
        }

        return ScrapInfo;

    }

}
