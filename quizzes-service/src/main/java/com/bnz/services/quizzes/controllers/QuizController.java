package com.bnz.services.quizzes.controllers;

import com.bnz.services.quizzes.models.QuestionResponse;
import com.bnz.services.quizzes.models.Quiz;
import com.bnz.services.quizzes.services.QuizService;
import com.bnz.shared.models.Response;
import com.bnz.shared.security.tokens.JWTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private final JWTokenHandler jwTokenHandler = new JWTokenHandler();
    @Autowired
    private QuizService quizService;

    @PostMapping("/new")
    public ResponseEntity<Response<Void>> newQuiz(@RequestHeader HttpHeaders headers, @RequestBody Quiz quiz) {
        try {
            Map<String, Object> data = jwTokenHandler.getTokenData(headers);
            quizService.createQuiz(quiz, data);
            return new ResponseEntity<>(new Response<>(true, "Successfully created quiz"), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<Response<Void>> startQuiz(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        try {
            Map<String, Object> data = jwTokenHandler.getTokenData(headers);
            quizService.startQuiz(id, (String) data.get("sub"));
            return new ResponseEntity<>(new Response<>(true, "Quiz started. Good luck!"), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Response<Void>> submitAnswer(@RequestHeader HttpHeaders headers, @PathVariable String id, @RequestBody List<QuestionResponse> questionResponseList) {
        try {
            Map<String, Object> data = jwTokenHandler.getTokenData(headers);
            quizService.processResponse(id, (String) data.get("sub"), questionResponseList);
            return new ResponseEntity<>(new Response<>(true, "Thanks for submission. We are now evaluating your answers."), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(new Response<>(false, e.getReason()), e.getStatus());
        }
    }

}
