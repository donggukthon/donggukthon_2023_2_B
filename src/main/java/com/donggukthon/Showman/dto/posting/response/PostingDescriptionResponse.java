package com.donggukthon.Showman.dto.posting.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostingDescriptionResponse {
    private Long postingId;
    private String snowmanNameDescription;
    private String snowmanName;

    public static PostingDescriptionResponse of(Long postingId, String snowmanNameDescription, String snowmanName) {
        return PostingDescriptionResponse.builder()
                .postingId(postingId)
                .snowmanNameDescription(snowmanNameDescription)
                .snowmanName(snowmanName)
                .build();
    }
}
