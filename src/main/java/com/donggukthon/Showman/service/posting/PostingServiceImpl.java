package com.donggukthon.Showman.service.posting;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.config.SecurityUtils;
import com.donggukthon.Showman.dto.posting.request.PostingDescriptionRequest;
import com.donggukthon.Showman.dto.posting.request.PostingLocationRequest;
import com.donggukthon.Showman.dto.posting.response.PostingDescriptionResponse;
import com.donggukthon.Showman.dto.posting.response.PostingImageResponse;
import com.donggukthon.Showman.dto.posting.response.PostingLocationResponse;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.repository.UserRepository;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{

    @Value("${spring.cloud.bucket}")
    private String bucketName;

    private final Storage storage;
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;

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
    public PostingLocationResponse postingLocation(PostingLocationRequest postingLocationRequest) {
        Posting posting = postingRepository.findById(postingLocationRequest.getPostingId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        posting.updatePostingLocation(postingLocationRequest.getLatitude(), postingLocationRequest.getLongitude(), postingLocationRequest.getAddress());

        return PostingLocationResponse.of(posting.getPostingId(), posting.getAddress(), posting.getSnowmanName(), posting.getSnowmanImageUrl(), posting.getCreatedAt());
    }
}
