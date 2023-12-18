package com.donggukthon.Showman.global.auth.token.jwt;

import java.util.Objects;
import com.donggukthon.Showman.global.auth.token.exception.InvalidTokenException;

public record JwtAuthenticationRecord(
        Long id,
        String accessToken

) {
    public JwtAuthenticationRecord {
        validateId(id);
        validateToken(accessToken);
    }

    private void validateToken(String accessToken) {
        if (Objects.isNull(accessToken) || accessToken.isBlank()) {
            throw new InvalidTokenException();
        }
    }

    private void validateId(Long userId) {
        if (Objects.isNull(userId) || userId <= 0L) {
            throw new InvalidTokenException();
        }
    }

    @Override
    public String toString() {
        return "JwtAuthentication{" +
                "accessToken='" + accessToken + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}