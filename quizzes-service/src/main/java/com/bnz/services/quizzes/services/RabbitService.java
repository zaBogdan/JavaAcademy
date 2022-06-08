package com.bnz.services.quizzes.services;

import com.bnz.services.quizzes.models.Attenders;
import com.bnz.services.quizzes.models.Quiz;
import com.bnz.services.quizzes.repository.QuizRepository;
import com.bnz.services.quizzes.utils.AttenderStatus;
import com.bnz.shared.models.QuestionResponse;
import com.bnz.shared.models.RabbitMQModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuizRepository quizRepository;

    public void callback(RabbitMQModel<?> data) {
        try{
            log.info("Callback started.");
            ObjectMapper mapper = new ObjectMapper();
            QuestionResponse question = mapper.convertValue(data.getData(), new TypeReference<>() {});
            Quiz quiz = quizRepository.findQuizById(question.getQuizId());
            List<Attenders> att = quiz.getAttenders();
            if(att == null) {
                throw new Exception("No attenders to question with id: "+quiz.getId());
            }
            var target = att.parallelStream().filter(x -> x.getUserId().equals(question.getUserId())).findFirst();
            if(target.isEmpty()) {
                throw new Exception("Cannot find user " + question.getUserId() + " in attenders list");
            }
            Attenders attender = target.get();

            if(attender.getDetailedScore().get(attender.getUserId()) != null) {
                attender.setTotalScore(attender.getTotalScore() + question.getScore());
            }
            // Check if all questions are completed before doing this
            if(attender.getDetailedScore().entrySet().size() == quiz.getQuestions().size()) {
                if(quiz.getCloseAt() != null && !quiz.isAcceptsAnswers() && attender.getFinishedAt().compareTo(quiz.getCloseAt()) >= 0) {
                    attender.setStatus(AttenderStatus.NOT_FINISHED_IN_TIME.getValue());
                } else {
                    attender.setStatus(AttenderStatus.FINISHED.getValue());
                }
            }
            attender.addScore(question.getQuestionUID(), question.getScore(), question.getResponse());
            quizRepository.save(quiz);
            log.info("Callback successfully ended and information added");
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
