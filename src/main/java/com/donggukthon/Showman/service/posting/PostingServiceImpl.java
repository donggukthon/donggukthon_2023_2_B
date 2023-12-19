package com.donggukthon.Showman.service.posting;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.config.SecurityUtils;
import com.donggukthon.Showman.dto.comment.response.CommentResponse;
import com.donggukthon.Showman.dto.posting.request.PostingDescriptionRequest;
import com.donggukthon.Showman.dto.posting.request.PostingLocationRequest;
import com.donggukthon.Showman.dto.posting.request.PostingUnscrap;
import com.donggukthon.Showman.dto.posting.response.*;
import com.donggukthon.Showman.entity.Comment;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.Scrap;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.*;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{

    @Value("${spring.cloud.bucket}")
    private String bucketName;

    private final Storage storage;
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final ScrapRepository scrapRepository;

    @Override
    @Transactional
    public PostingImageResponse postingImage(MultipartFile multipartFile) throws IOException {

        Long userId = null;
        try {
            userId = SecurityUtils.getCurrentUserId();
        }catch (AuthenticationException e){
            throw new RuntimeException(e);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_USER));

        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = multipartFile.getContentType(); // 파일의 형식 ex) JPG

        // 이미지 업로드
        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucketName, uuid).setContentType(ext).build(),multipartFile.getInputStream());

        Posting posting = Posting.builder().snowmanImageUrl("https://storage.googleapis.com/"+bucketName+"/"+uuid).build();
        postingRepository.save(posting);

        posting.setUser(user);

        return PostingImageResponse.of(posting.getPostingId());
    }

    @Override
    @Transactional
    public PostingDescriptionResponse postingDescription(PostingDescriptionRequest postingDescriptionRequest) {
        Posting posting = postingRepository.findById(postingDescriptionRequest.getPostingId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        posting.updatePostingDescription(postingDescriptionRequest.getSnowmanName(), postingDescriptionRequest.getSnowmanNameDescription());

        return PostingDescriptionResponse.of(posting.getPostingId(), posting.getSnowmanName(), posting.getSnowmanDescription());
    }

    @Override
    @Transactional
    public PostingLocationResponse postingLocation(PostingLocationRequest postingLocationRequest) {
        Posting posting = postingRepository.findById(postingLocationRequest.getPostingId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        posting.updatePostingLocation(postingLocationRequest.getLatitude(), postingLocationRequest.getLongitude(), postingLocationRequest.getAddress());

        return PostingLocationResponse.of(posting.getPostingId(), posting.getAddress(), posting.getSnowmanName(), posting.getSnowmanImageUrl(), posting.getCreatedAt());
    }

    @Override
    @Transactional(readOnly = true)
    public PostingResponse getPosting(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        List<Comment> comments = commentRepository.findAllByPosting(posting);

        List<CommentResponse> commentResponses = null;

        for (Comment comment : comments) {
            User user = comment.getUser();
            CommentResponse commentResponse =
                    CommentResponse.of(comment.getCommentId(), comment.getContent(), comment.getUser().getUserId(), user.getNickname(), comment.getCreatedAt());

            commentResponses.add(commentResponse);
        }

        Integer heartCnt = heartRepository.countByPostingPostingId(postingId);
        Integer commentCnt  = comments.size();

        return PostingResponse.of(posting.getPostingId(), posting.getAddress(), posting.getSnowmanName(),
                posting.getSnowmanImageUrl(), posting.getSnowmanDescription(), heartCnt, commentCnt, commentResponses);
    }

    @Override
    @Transactional
    public PostingScrapResponse scrapPosting(Long postingId) {
        Long userId = null;
        try {
            userId = SecurityUtils.getCurrentUserId();
        }catch (AuthenticationException e){
            throw new RuntimeException(e);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_USER));
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));

        Scrap scrap = Scrap.builder().user(user).posting(posting).build();
        scrapRepository.save(scrap);

        return PostingScrapResponse.of(scrap.getScrapId());
    }

    @Override
    @Transactional
    public PostingScrapResponse unscrapPosting(PostingUnscrap postingUnscrap) {

        scrapRepository.deleteById(postingUnscrap.getScrapId());

        return PostingScrapResponse.of(postingUnscrap.getScrapId());
    }
}
