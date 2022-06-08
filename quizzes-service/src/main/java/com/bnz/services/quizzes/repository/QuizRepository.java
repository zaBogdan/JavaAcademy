package com.bnz.services.quizzes.repository;

import com.bnz.services.quizzes.models.Quiz;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, String> {
    Quiz findQuizById(String id);
    @Query(fields="{'_id' : 0, 'attenders' :0, 'ownerId': 0, 'questions':  0}")
    List<Quiz> findQuizByAcceptsAnswersEqualsOrderByUpdatedAt(boolean accepts);
}
