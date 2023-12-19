package com.donggukthon.Showman.dto.posting.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostingImageResponse {
    private Long postingId;

    public static PostingImageResponse of(Long postingId) {
        return PostingImageResponse.builder()
                .postingId(postingId)
                .build();
    }
}
