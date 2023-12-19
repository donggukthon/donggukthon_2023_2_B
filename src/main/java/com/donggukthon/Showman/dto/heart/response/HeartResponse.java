package com.donggukthon.Showman.dto.heart.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeartResponse {

    private Long heartId;

    public static HeartResponse of(Long heartId) {
        return HeartResponse.builder()
                .heartId(heartId)
                .build();
    }
}
