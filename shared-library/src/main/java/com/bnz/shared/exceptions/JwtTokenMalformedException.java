package com.bnz.services.gateway.exceptions;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
