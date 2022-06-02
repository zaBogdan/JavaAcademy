package com.bnz.services.plsqlcompiler.controllers;

import com.bnz.services.plsqlcompiler.models.Request;
import com.bnz.services.plsqlcompiler.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tests/sql")
public class SQLController {
    @PostMapping(value="/submit", consumes = "application/json")
    public ResponseEntity<Response<Request>> acceptAnswers(@RequestBody Request req) {
        try {
            return new ResponseEntity<>(new Response<>(true, "Successfully submitted test", req), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }
}
