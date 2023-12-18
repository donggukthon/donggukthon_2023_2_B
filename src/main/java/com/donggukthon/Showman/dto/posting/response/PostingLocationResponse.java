package com.donggukthon.Showman.dto.posting.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostingLocationResponse {

    private Long postingId;
    private String address;
    private String snowmanName;
    private String snowmanImageUrl;
    private LocalDateTime createdAt;

    public static PostingLocationResponse of(Long postingId, String address, String snowmanName, String snowmanImageUrl, LocalDateTime createdAt) {
        return PostingLocationResponse.builder()
                .postingId(postingId)
                .address(address)
                .snowmanName(snowmanName)
                .snowmanImageUrl(snowmanImageUrl)
                .build();
    }
}
