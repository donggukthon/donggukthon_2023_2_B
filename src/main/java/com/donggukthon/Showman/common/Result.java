package com.donggukthon.Showman.common;

import lombok.Getter;

@Getter
public enum Result {
// Common
    OK(200, "성공"),
    FAIL(400,"실패"),

    // token
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    UNAUTHORIZED_REQUEST(401,"권한이 없습니다."),
    FORBIDDEN_REQUEST(403, "접근이 금지되었습니다."),
    EXPIRED_TOKEN(403, "만료된 토큰입니다."),
    INVALID_TOKEN_SIGNATURE(403, "잘못된 토큰 서명입니다."),
    INVALID_TOKEN_FORMAT(403, "잘못된 토큰 형식입니다."),

    // user
    NOT_FOUND_USER(404, "존재하지 않는 사용자입니다."),

    // posting
    NOT_FOUND_POSTING(404, "존재하지 않는 게시글입니다."),
    NOT_FOUND_COMMENT(404, "존재하지 않는 댓글입니다."),


    NOT_FOUND_HEART(404, "존재하지 않는 좋아요입니다."),


    NOT_FOUND_SCRAP(404, "존재하지 않는 스크랩입니다."),   ;

    private final String message;
    private final int status;

    Result(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    public int getStatus() {
        return status;
    }
}