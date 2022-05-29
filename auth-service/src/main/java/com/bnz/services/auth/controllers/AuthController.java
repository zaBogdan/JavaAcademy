package com.bnz.services.auth.controllers;

import com.bnz.services.auth.models.User;
import com.bnz.services.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping(value = "/ehlo")
    public ResponseEntity<String> getUser() {
        return new ResponseEntity<>("Hello world and bros!",HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        authService.create(user);
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
