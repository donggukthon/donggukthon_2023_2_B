package com.donggukthon.Showman.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public static <T> CommonResponse<T> success(T result) {
        return CommonResponse.<T>builder()
                .status(Result.OK.getStatus())
                .message(Result.OK.getMessage())
                .result(result)
                .build();
    }
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .status(Result.OK.getStatus())
                .message(Result.OK.getMessage())
                .build();
    }

    public static <T> CommonResponse<T> fail() {
        return CommonResponse.<T>builder()
                .status(Result.FAIL.getStatus())
                .message(Result.FAIL.getMessage())
                .build();
    }

    public static <T> CommonResponse<T> fail(Result result) {
        return CommonResponse.<T>builder()
                .status(result.getStatus())
                .message(result.getMessage())
                .build();
    }


    @Builder
    public CommonResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}