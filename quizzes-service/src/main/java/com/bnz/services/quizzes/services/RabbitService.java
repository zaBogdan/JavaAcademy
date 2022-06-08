package com.bnz.services.quizzes.services;

import com.bnz.services.quizzes.repository.QuizRepository;
import com.bnz.shared.models.RabbitMQModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    @Autowired
    private QuizRepository quizRepository;

    public static void callback(RabbitMQModel<?> data) {
        System.out.println(data);
    }
}
