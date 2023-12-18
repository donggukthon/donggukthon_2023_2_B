package com.donggukthon.Showman.service.user;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.config.SecurityUtils;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public Long getCurrentUserId() throws AuthenticationException {
        return SecurityUtils.getCurrentUserId();
    }

    // 현재 사용자 조회
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        try {
            return userRepository
                    .findById(SecurityUtils.getCurrentUserId()).get();
        } catch (Exception e) {
            throw new CustomException(Result.NOT_FOUND_USER);
        }
    }
}
