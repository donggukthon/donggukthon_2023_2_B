package com.donggukthon.Showman.service.heart;

import com.donggukthon.Showman.dto.heart.request.HeartLikeRequest;
import com.donggukthon.Showman.dto.heart.request.HeartUnLikeRequest;
import com.donggukthon.Showman.dto.heart.response.HeartResponse;

public interface HeartService {
    HeartResponse like(HeartLikeRequest heartLikeRequest);

    HeartResponse unLike(HeartUnLikeRequest heartUnLikeRequest);
}
