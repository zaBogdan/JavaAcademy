package com.bnz.services.quizzes.utils.rabbitmq;

import com.bnz.services.quizzes.services.RabbitService;
import com.bnz.shared.models.RabbitMQModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "${bnz.rabbitmq.responseQueue}")
    public void consumer(RabbitMQModel<?> data) {
        RabbitService.callback(data);
    }
}
