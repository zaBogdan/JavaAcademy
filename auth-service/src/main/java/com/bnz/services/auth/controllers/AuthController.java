package com.bnz.services.auth.controllers;

import com.bnz.services.auth.models.User;
import com.bnz.services.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.security.Principal;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private AuthService authService;

//    @GetMapping(value = "/current")
//    public Principal getUser(Principal principal) {
//        return principal;
//    }

    @PostMapping()
    public void createUser(@RequestBody User user) {
        authService.create(user);
    }
}
