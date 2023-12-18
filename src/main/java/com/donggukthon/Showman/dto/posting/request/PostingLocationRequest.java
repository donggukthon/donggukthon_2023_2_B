package com.donggukthon.Showman.dto.posting.request;

import lombok.Getter;

@Getter
public class PostingLocationRequest {

    private Long postingId;
    private String latitude;
    private String longitude;
    private String address;
}
