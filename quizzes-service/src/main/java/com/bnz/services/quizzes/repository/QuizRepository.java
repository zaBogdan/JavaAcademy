package com.bnz.services.quizzes.repository;

import com.bnz.services.quizzes.models.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, String> {
}
