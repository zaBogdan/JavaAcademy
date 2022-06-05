package com.bnz.services.auth.services;

import com.bnz.services.auth.respository.ReferralRepository;
import com.bnz.shared.models.Referral;
import com.bnz.shared.models.User;
import com.bnz.services.auth.respository.UserRepository;
import com.bnz.shared.security.passwords.BCryptHash;
import com.bnz.shared.users.roles.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferralRepository referralRepository;


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
        user.setRole(Roles.DEFAULT.getValue());
        log.info("Successfully got current user!");
        return currentUser;
    }

    public void create(User user, String referral) {
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
        User newUser = userRepository.save(user);
        if(referral != null) {
            Referral ref =  referralRepository.findByReferral(referral);
            if(ref == null) {
                return;
            }
            ref.addNewReferredUser(newUser);
            referralRepository.save(ref);
        }
    }
}
