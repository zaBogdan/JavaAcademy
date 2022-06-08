package com.bnz.services.quizzes.services;

import com.bnz.services.quizzes.models.Attenders;
import com.bnz.services.quizzes.models.Question;
import com.bnz.services.quizzes.models.QuestionResponse;
import com.bnz.services.quizzes.models.Quiz;
import com.bnz.services.quizzes.repository.QuizRepository;
import com.bnz.services.quizzes.repository.UserRepository;
import com.bnz.services.quizzes.utils.AttenderStatus;
import com.bnz.shared.constants.RabbitMQConstants;
import com.bnz.shared.models.RabbitMQModel;
import com.bnz.shared.models.User;
import com.bnz.services.quizzes.utils.rabbitmq.Publisher;
import com.bnz.shared.users.roles.Roles;
import com.bnz.shared.users.roles.RolesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Publisher publisher;

    public void createQuiz(Quiz quiz, Map<String, Object> data) {
        if(!RolesUtil.hasMinimumRequiredRole((int)data.get("role"), Roles.TEACHER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must have a teacher role assigned to create a test.");
        }
        quiz.setOwnerId((String) data.get("sub"));
        quiz.setCreatedAt(new Date());
        quiz.setUpdatedAt(new Date());
        quizRepository.save(quiz);
    }

    public void startQuiz(String quizId, String userId) throws ResponseStatusException {
        Attenders att =  new Attenders();
        Quiz quiz = quizRepository.findQuizById(quizId);
        if(quiz == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't find quiz.");
        }
        validateQuiz(quiz);

        att.setUserId(userId);
        att.setStatus(AttenderStatus.STARTED.getValue());
        att.setStartedAt(new Date());
        att.setScore(0);
        quiz.attendersQuestion(att);
        quizRepository.save(quiz);
    }

    public void processResponse(String quizId, String userId, List<QuestionResponse> responseList) throws ResponseStatusException {
        Quiz quiz = quizRepository.findQuizById(quizId);
        if(quiz == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't find quiz.");
        }
        if(quiz.getAttenders() == null || quiz.getAttenders().size() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You didn't start the quiz.");
        }
        Attenders attender = quiz.getAttenders().parallelStream().filter(x -> x.getUserId().equals(userId)).findFirst().get();
        if(attender == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You didn't start the quiz.");
        }
        try {
            validateQuiz(quiz);
            attender.setStatus(AttenderStatus.FINISHED.getValue());
        }catch (ResponseStatusException e) {
            System.err.println(e.getMessage());
            attender.setStatus(AttenderStatus.NOT_FINISHED_IN_TIME.getValue());
        }
        if(attender.getFinishedAt() != null || attender.getStatus() != AttenderStatus.STARTED.getValue()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You've already responded to this quiz");
        }
        attender.setFinishedAt(new Date());

        for(QuestionResponse questionResponse : responseList) {
            Question question = quiz.getQuestions().parallelStream().filter(x -> x.getUid().equals(questionResponse.getQuestionUID())).findFirst().get();
            questionResponse.setMaximumScore(question.getScore());
            questionResponse.setLanguage(question.getLanguage());
            questionResponse.setQuizId(quizId);
            if(question.getType() == 1) {
                attender.setStatus(AttenderStatus.IN_VERIFICATION_PROCESS.getValue());
                // publish to rabbit
                publisher.send(RabbitMQConstants.ROUTING_KEY_PROCESSING,new RabbitMQModel<>("question-compiler", "quizzes-service", questionResponse));
            } else {
                // check for single/multiple choice
                // TODO: here we should add support
            }
        }
        quizRepository.save(quiz);
    }

    public Attenders getAttenderResponseForQuiz(String quizId, String userId) {
        Quiz quiz = quizRepository.findQuizById(quizId);
        if(quiz == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't find quiz.");
        }
        Attenders attender = quiz.getAttenders().parallelStream().filter(x -> x.getUserId().equals(userId)).findFirst().get();
        if(attender == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have no submitted answers to this quiz");
        }
        if(attender.getQuestionInformation() == null) {
            throw new ResponseStatusException(HttpStatus.OK, "Evaluation is still in progress. Check it out in a few moments.");
        }
        attender.setUserId(null);
        // TODO: Get here the details of each question
        return attender;
    }

    public Quiz getQuizReportInformation(String quizId, Map<String, Object> data) {
        if(!RolesUtil.hasMinimumRequiredRole((int)data.get("role"), Roles.TEACHER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must have a teacher role assigned to create a test.");
        }
        Quiz quiz = quizRepository.findQuizById(quizId);
        if(quiz == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't find quiz.");
        }
        if(!quiz.getOwnerId().equals(data.get("sub"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This quiz doesn't belong to you");
        }
        quiz.setOwnerId(null);
        quiz.setId(null);
        if(quiz.getAttenders() != null) {
            for(Attenders att: quiz.getAttenders()) {
                User user = userRepository.findById(att.getUserId()).get();
                user.setId(null);
                user.setPassword(null);
                att.setUser(user);
                att.setUserId(null);
            }
        }
        return quiz;
    }

    public void updateQuiz(String quizId, Map<String, Object> data) {

    }

    private void validateQuiz(Quiz quiz) throws ResponseStatusException {
        if(!quiz.isAcceptsAnswers()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quiz is currently closed.");
        }
        if(quiz.getCloseAt() != null) {
            if(quiz.getCloseAt().compareTo(new Date()) <= 0) {
                quiz.setAcceptsAnswers(false);
                quizRepository.save(quiz);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quiz is currently closed.");
            }
        }
    }
}
