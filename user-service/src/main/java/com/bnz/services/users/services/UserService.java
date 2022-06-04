package com.bnz.services.users.services;

import com.bnz.services.users.repository.UserRepository;
import com.bnz.shared.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(String id) {
        log.info("Getting current user");
        return userRepository.getUserById(id);
    }
}
