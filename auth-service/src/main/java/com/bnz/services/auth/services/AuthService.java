package com.bnz.services.auth.services;

import com.bnz.services.auth.models.User;
import com.bnz.services.auth.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        log.debug("This is the request body: "+ user.toString());
    }
}
