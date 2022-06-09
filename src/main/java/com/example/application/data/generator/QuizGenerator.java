package com.example.application.data.generator;

import com.example.application.data.entity.Quiz;
import com.example.application.data.service.QuizRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class QuizGenerator {

    @Bean
    public CommandLineRunner loadQuiz(QuizRepository quizRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());

            logger.info("Generating demo data");

            logger.info("... generating 4 Quiz entities...");
            Quiz javaQuiz = new Quiz();
            javaQuiz.setProgrammingLanguage("Java");
            javaQuiz.setLevel("Entry");
            javaQuiz.setDuration(10);
            quizRepository.save(javaQuiz);

            Quiz jsQuizBeginner = new Quiz();
            jsQuizBeginner.setProgrammingLanguage("JavaScript");
            jsQuizBeginner.setLevel("Beginner");
            jsQuizBeginner.setDuration(20);
            quizRepository.save(jsQuizBeginner);

            Quiz phpQuizAdvanced = new Quiz();
            phpQuizAdvanced.setProgrammingLanguage("PHP");
            phpQuizAdvanced.setLevel("Advanced");
            phpQuizAdvanced.setDuration(60);
            quizRepository.save(phpQuizAdvanced);

            Quiz javaQuizIntermediate = new Quiz();
            javaQuizIntermediate.setProgrammingLanguage("Java");
            javaQuizIntermediate.setLevel("Intermediate");
            javaQuizIntermediate.setDuration(25);
            quizRepository.save(javaQuizIntermediate);

            logger.info("Generated demo data");
        };
    }

}