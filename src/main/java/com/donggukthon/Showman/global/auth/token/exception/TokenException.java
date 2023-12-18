package com.donggukthon.Showman.global.auth.token.exception;

import com.donggukthon.Showman.global.base.ErrorCode;
import com.donggukthon.Showman.global.base.ServiceException;

public abstract class TokenException extends ServiceException {

    protected TokenException(ErrorCode errorCode, String messageKey) {
        super(errorCode, messageKey, null);
    }
}