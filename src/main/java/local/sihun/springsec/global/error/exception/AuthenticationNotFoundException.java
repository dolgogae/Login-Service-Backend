package local.sihun.springsec.global.error.exception;

import local.sihun.springsec.global.error.ErrorCode;

public class AuthenticationNotFoundException extends BusinessException {
    public AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }
}