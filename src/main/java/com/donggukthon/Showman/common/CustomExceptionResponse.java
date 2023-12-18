package com.donggukthon.Showman.common;

import lombok.*;

@Getter
@Setter
public class CustomExceptionResponse extends RuntimeException {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class entryPointResponse<T>{
        private Integer code;
        private String message;
        private T data;

        public static entryPointResponse response(CustomException customException){
            return entryPointResponse
                    .builder()
                    .code(customException.getResult().getStatus())
                    .message(customException.getResult().getMessage())
                    .data(null)
                    .build();
        }
    }
}