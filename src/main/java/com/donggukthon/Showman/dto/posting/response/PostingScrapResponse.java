package com.donggukthon.Showman.dto.posting.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostingScrapResponse {

    private Long scrapId;

    public static PostingScrapResponse of(Long scrapId) {
        return PostingScrapResponse.builder()
                .scrapId(scrapId)
                .build();
    }
}
