package com.donggukthon.Showman.dto.user.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/* response : positng id , snowman title, snowman image url,
 * posting create at, address
 * heart cnt , comment cnt*/

@Builder
@Getter
public class PostInfoResponse {
    private Long postingId;
    private String snowmanName; /* title */
    private String snowmanImageUrl;
    private LocalDateTime createAt; // 눈사람 등록 일시
    private String address;
    private Long heartCnt;
    private Long commentCnt;

    public static PostInfoResponse of (
            Long postingId, String snowmanName, String snowmanImageUrl
            ,LocalDateTime createAt, String address, Long heartCnt, Long commentCnt
    ){
        return PostInfoResponse.builder()
                .postingId(postingId).snowmanName(snowmanName)
                .createAt(createAt).snowmanImageUrl(snowmanImageUrl)
                .address(address).heartCnt(heartCnt).commentCnt(commentCnt)
                .build();
    }
}

