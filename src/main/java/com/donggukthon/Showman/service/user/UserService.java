package com.donggukthon.Showman.service.user;

import com.donggukthon.Showman.entity.User;
import javax.naming.AuthenticationException;

public interface UserService {

    Long getCurrentUserId() throws AuthenticationException;

    User getCurrentUser();
}
