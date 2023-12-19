package com.donggukthon.Showman.dto.posting.response;

import com.donggukthon.Showman.dto.comment.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostingResponse {

    private Long postingId;
    private String address;
    private String snowmanName;
    private String snowmanImageUrl;
    private String snowmanDescription;

    private Integer heartCnt;
    private Integer commentCnt;

    private List<CommentResponse> commentResponses;

    public static PostingResponse of(Long postingId, String address, String snowmanName, String snowmanImageUrl, String snowmanDescription, Integer heartCnt, Integer commentCnt, List<CommentResponse> commentResponses) {
        return PostingResponse.builder()
                .postingId(postingId)
                .address(address)
                .snowmanName(snowmanName)
                .snowmanImageUrl(snowmanImageUrl)
                .snowmanDescription(snowmanDescription)
                .heartCnt(heartCnt)
                .commentCnt(commentCnt)
                .commentResponses(commentResponses)
                .build();
    }
}
