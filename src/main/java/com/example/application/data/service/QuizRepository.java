package com.example.application.data.service;

import com.example.application.data.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    @Query("select q from Quiz q")
    List<Quiz> search();

    @Query("select q from Quiz q " +
            "where q.programmingLanguage like :programmingLanguage")
    List<Quiz> searchForProgrammingLanguage(@Param("programmingLanguage") String programmingLanguage);
}
