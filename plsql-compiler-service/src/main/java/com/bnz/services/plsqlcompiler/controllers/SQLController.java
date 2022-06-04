package com.bnz.services.plsqlcompiler.controllers;

import com.bnz.services.plsqlcompiler.models.Request;
import com.bnz.services.plsqlcompiler.models.Response;
import com.bnz.services.plsqlcompiler.services.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tests/sql")
public class SQLController {
    @Autowired
    private SQLService sqlService;

    @PostMapping(value="/submit", consumes = "application/json")
    public ResponseEntity<Response<String>> acceptAnswers(@RequestBody Request req) {
        try {
            return new ResponseEntity<>(new Response<>(true, "Successfully submitted test", sqlService.execute(req.getQuery())), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }

    @GetMapping(value="/stats")
    public ResponseEntity<Response<String>> getStats() {
        try {
            return new ResponseEntity<>(new Response<>(true, "Successfully submitted test", sqlService.statistics("zaBogdan")), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }
}