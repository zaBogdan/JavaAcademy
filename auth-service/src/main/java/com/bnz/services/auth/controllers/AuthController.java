package com.bnz.services.auth.controllers;

import com.bnz.shared.models.Response;
import com.bnz.shared.models.User;
import com.bnz.services.auth.services.AuthService;
import com.bnz.shared.security.tokens.JWTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private final JWTokenHandler jwTokenHandler = new JWTokenHandler();

    @PostMapping(value="/login", consumes = "application/json")
    public ResponseEntity<Response<Map<String, String>>> loginUser(@RequestBody User user) {
        try {
            User authUser = authService.login(user);
            return new ResponseEntity<>(new Response<>(true, "Successfully logged in", jwTokenHandler.generateTokenPair(authUser)), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }

    @PostMapping(value="/register", consumes = "application/json")
    public ResponseEntity<Response<Void>> createUser(@RequestBody User user) {
        try {
            authService.create(user);
            return new ResponseEntity<>(new Response<>(true, "Successfully created user"), HttpStatus.OK);
        } catch(ResponseStatusException e) {
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }
}
