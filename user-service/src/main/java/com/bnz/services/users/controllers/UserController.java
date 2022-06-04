package com.bnz.services.users.controllers;

import com.bnz.services.users.services.UserService;
import com.bnz.shared.models.Response;
import com.bnz.shared.models.User;
import com.bnz.shared.security.JWTokenHandler;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private final JWTokenHandler jwTokenHandler = new JWTokenHandler();

    @GetMapping("/self")
    public ResponseEntity<Response<User>> getCurrentUser(@RequestHeader HttpHeaders headers) {
        Claims claims = jwTokenHandler.getDataFromTokens(headers);
        return new ResponseEntity<>(new Response<>(true, "Successfully fetched current user!c",userService.getCurrentUser(claims.getSubject())), HttpStatus.OK);
    }
}
