package com.bnz.services.users.controllers;

import com.bnz.services.users.models.RoleOperation;
import com.bnz.services.users.services.UserService;
import com.bnz.shared.models.Response;
import com.bnz.shared.models.User;
import com.bnz.shared.security.tokens.JWTokenHandler;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private final JWTokenHandler jwTokenHandler = new JWTokenHandler();

    @GetMapping("/self")
    public ResponseEntity<Response<User>> getCurrentUser(@RequestHeader HttpHeaders headers) {
        try {
            Claims claims = jwTokenHandler.getDataFromTokens(headers);
            User currentUser = userService.getCurrentUser(claims.getSubject());
            currentUser.setPassword(null);
            return new ResponseEntity<>(new Response<>(true, "Successfully fetched current user!",currentUser), HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(new Response<>(false, ex.getReason()), ex.getStatus());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Response<User>> modifyCurrentUser(@RequestHeader HttpHeaders headers, @RequestBody User modifiedUser) {
        try {
            Claims claims = jwTokenHandler.getDataFromTokens(headers);
            if(modifiedUser.getPassword() != null && !((boolean) claims.getOrDefault("fresh", false))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't change the password. Try to login again and then change the password");
            }
            return new ResponseEntity<>(new Response<>(true, "Successfully fetched current user!",userService.modifyCurrentUser(claims.getSubject(), modifiedUser)), HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(new Response<>(false, ex.getReason()), ex.getStatus());
        }
    }

    @PostMapping("/role")
    public ResponseEntity<Response<Void>> appendRole(@RequestHeader HttpHeaders headers, @RequestBody RoleOperation body) {
        try {
            Claims claims = jwTokenHandler.getDataFromTokens(headers);
            String role = userService.appendRole(claims.getSubject(), body);
            return new ResponseEntity<>(new Response<>(true, String.format("Successfully added `%s` to yourself!",role)), HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(new Response<>(false, ex.getReason()), ex.getStatus());
        }
    }

}
