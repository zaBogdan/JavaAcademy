package com.bnz.services.gateway.exceptions;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String s) {
        super(s);
    }
}
