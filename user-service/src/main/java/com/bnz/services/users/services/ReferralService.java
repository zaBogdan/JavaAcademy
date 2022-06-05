package com.bnz.services.users.services;

import com.bnz.shared.models.Referral;
import com.bnz.services.users.repository.ReferralRepository;
import com.bnz.shared.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ReferralService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${globals.backend.api}")
    private final String backendURL = "https://java-api.pground.io";

    @Autowired
    private UserService userService;
    @Autowired
    private ReferralRepository referralRepository;
    public String getReferral(String id) {
        User user = userService.getCurrentUser(id);
        Referral ref = referralRepository.getByUserId(user.getId());
        if(ref == null) {
            ref = new Referral();
            ref.setUserId(user.getId());
            ref.setReferredUsers(new ArrayList<>());
            ref.setReferral(UUID.randomUUID().toString());
            referralRepository.save(ref);
        }
        return String.format("%s/auth/register?ref=%s", backendURL, ref.getReferral());
    }

    public Referral getReferralInfo(String id) {
        User user = userService.getCurrentUser(id);
        Referral ref = referralRepository.getByUserId(user.getId());
        if (ref == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have a referral link yet. Try to join our program first!");
        }
        ref.setUserId(null);
        return ref;
    }
}
