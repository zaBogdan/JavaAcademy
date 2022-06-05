package com.bnz.services.quizzes.services;

import com.bnz.services.quizzes.models.Quiz;
import com.bnz.services.quizzes.repository.QuizRepository;
import com.bnz.shared.users.roles.Roles;
import com.bnz.shared.users.roles.RolesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;

@Service
public class QuizService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuizRepository quizRepository;

    public void createQuiz(Quiz quiz, Map<String, Object> data) {
        if(!RolesUtil.hasMinimumRequiredRole((int)data.get("role"), Roles.TEACHER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must have a teacher role assigned to create a test.");
        }
        quiz.setCreatedAt(new Date());
        quizRepository.save(quiz);
    }

}
