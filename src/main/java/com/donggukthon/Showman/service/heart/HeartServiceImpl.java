package com.donggukthon.Showman.service.heart;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.dto.heart.request.HeartLikeRequest;
import com.donggukthon.Showman.dto.heart.request.HeartUnLikeRequest;
import com.donggukthon.Showman.dto.heart.response.HeartResponse;
import com.donggukthon.Showman.entity.Heart;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.HeartRepository;
import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartServiceImpl implements HeartService{

    private final HeartRepository heartRepository;
    private final UserServiceImpl userServiceImpl;
    private final PostingRepository postingRepository;

    @Override
    @Transactional
    public HeartResponse like(HeartLikeRequest heartLikeRequest) {
        User user = userServiceImpl.getCurrentUser();
        Posting posting = postingRepository.findById(heartLikeRequest.getPostingId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        Heart heart = Heart.builder()
                .user(user)
                .posting(posting)
                .build();

        heartRepository.save(heart);

        return HeartResponse.builder()
                .heartId(heart.getHeartId())
                .build();
    }

    @Override
    @Transactional
    public HeartResponse unLike(HeartUnLikeRequest heartUnLikeRequest) {
        Heart heart = heartRepository.findById(heartUnLikeRequest.getHeartId()).orElseThrow(() -> new CustomException(Result.NOT_FOUND_HEART));
        heartRepository.delete(heart);

        return HeartResponse.builder()
                .heartId(heart.getHeartId())
                .build();
    }
}
