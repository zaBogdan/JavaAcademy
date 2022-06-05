package com.bnz.services.users.controllers;


import com.bnz.shared.models.Referral;
import com.bnz.services.users.services.ReferralService;
import com.bnz.shared.models.Response;
import com.bnz.shared.security.tokens.JWTokenHandler;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user/referral")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    @Autowired
    private final JWTokenHandler jwTokenHandler = new JWTokenHandler();

    @GetMapping
    public ResponseEntity<Response<String>> getReferral(@RequestHeader HttpHeaders headers) {
        try {
            Claims claims = jwTokenHandler.getDataFromTokens(headers);
            return new ResponseEntity<>(new Response<>(true, "Successfully got the referral!", referralService.getReferral(claims.getSubject())), HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(new Response<>(false, ex.getReason()), ex.getStatus());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<Response<Referral>> getReferralInfo(@RequestHeader HttpHeaders headers) {
        try {
            Claims claims = jwTokenHandler.getDataFromTokens(headers);
            return new ResponseEntity<>(new Response<>(true, "Successfully got the referral!", referralService.getReferralInfo(claims.getSubject())), HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(new Response<>(false, ex.getReason()), ex.getStatus());
        }
    }

}
