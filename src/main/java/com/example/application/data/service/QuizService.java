package com.example.application.data.service;

import com.example.application.data.entity.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> findAllQuizzes() {
        return quizRepository.search();
    }

    public int countQuizzes() {
        return quizRepository.search().size();
    }

    public int getProgrammingLanguageCount(String programmingLanguage) {
        return quizRepository.searchForProgrammingLanguage(programmingLanguage).size();
    }
}