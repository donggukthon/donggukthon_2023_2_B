package com.donggukthon.Showman.dto.posting.request;

import lombok.Getter;

@Getter
public class PostingDescriptionRequest {
    private Long postingId;
    private String snowmanName;
    private String snowmanNameDescription;
}
