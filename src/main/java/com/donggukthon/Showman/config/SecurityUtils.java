package com.donggukthon.Showman.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    private static List<SimpleGrantedAuthority> notUserAuthority = new ArrayList<>();

    public static Long getCurrentUserId() throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AuthenticationException("인증 객체 로드불가");
        }

        if (authentication.isAuthenticated()
                && !CollectionUtils.containsAny(
                authentication.getAuthorities(), notUserAuthority)) {
            return Long.valueOf(authentication.getName());
        }

        return 0L;
    }
}
