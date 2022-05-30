package com.bnz.services.auth.services;

import com.bnz.services.auth.models.User;
import com.bnz.services.auth.respository.UserRepository;
import com.bnz.services.auth.utils.passwords.BCryptHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;


@Service
public class AuthService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;
    public User login(User user) {
        User currentUser = null;
        if(user.getEmail() != null) {
            currentUser = userRepository.findByEmail(user.getEmail());
        } else if(user.getUsername() != null) {
            currentUser = userRepository.findByUsername(user.getUsername());
        }
        if(currentUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email doesn't match with password.");
        }
        if(!BCryptHash.verify(currentUser.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password.");
        }
        return currentUser;
    }

    public void create(User user) {
        System.out.println(user.toString());
        User oldUser = userRepository.findByUsername(user.getUsername());
        if(oldUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email or username already exists");
        }
        oldUser = userRepository.findByEmail(user.getEmail());
        if(oldUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email or username already exists");
        }
        user.setPassword(
                BCryptHash.hash(user.getPassword())
        );
        log.debug("This is the request body: "+ user.toString());
        userRepository.save(user);
    }
}
