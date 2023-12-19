package com.donggukthon.Showman.dto.posting.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostingScrapResponse {

    private Long postingId;

    public static PostingScrapResponse of(Long postingId) {
        return PostingScrapResponse.builder()
                .postingId(postingId)
                .build();
    }
}
