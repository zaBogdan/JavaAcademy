package com.bnz.services.users.services;

import com.bnz.services.users.models.RoleOperation;
import com.bnz.services.users.repository.ReferralRepository;
import com.bnz.services.users.repository.UserRepository;
import com.bnz.shared.models.User;
import com.bnz.shared.security.passwords.BCryptHash;
import com.bnz.shared.users.roles.Roles;
import com.bnz.shared.users.roles.RolesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferralRepository referralRepository;

    public User getCurrentUser(String id) {
        User user = userRepository.getUserById(id);
        return user;
    }

    public User modifyCurrentUser(String id, User modifiedUser) {
        User user = getCurrentUser(id);
        if(modifiedUser.getFirstName() != null) {
            user.setFirstName(modifiedUser.getFirstName());
        }
        if(modifiedUser.getLastName() != null) {
            user.setLastName(modifiedUser.getLastName());
        }
        if(modifiedUser.getPassword() != null) {
            user.setPassword(BCryptHash.hash(modifiedUser.getPassword()));
        }
        if(modifiedUser.getEmail() != null) {
            user.setEmail(modifiedUser.getEmail());
        }
        userRepository.save(user);
        user.setPassword(null);
        return user;
    }

    public String appendRole(String id, RoleOperation body) {
        User user = getCurrentUser(id);
        if(body.getRemove() != null && body.getAppend() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't both append and remove a role!");
        }
        boolean append = false;
        String role = null;
        if(body.getAppend() != null) {
            append = true;
            role = body.getAppend();
        } else if(body.getRemove() != null) {
            role = body.getRemove();
        }

        if(role == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role name");
        int value = RolesUtil.getFromString(role).getValue();

        if((value & Roles.ADMINISTRATOR.getValue()) != 0 && !RolesUtil.hasMinimumRequiredRole(user.getRole(), Roles.ADMINISTRATOR))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have enough permissions");


        if (append) {
            user.addNewRole(value);
        } else {
            user.deleteRole(value);
        }
        userRepository.save(user);
        return role;
    }


}
