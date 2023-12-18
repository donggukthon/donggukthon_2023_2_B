package com.donggukthon.Showman.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER");
    private final String key;
}
