package com.bnz.services.quizzes.utils.rabbitmq;

import com.bnz.services.quizzes.services.RabbitService;
import com.bnz.shared.models.RabbitMQModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private RabbitService rabbitService = new RabbitService();
    @RabbitListener(queues = "${bnz.rabbitmq.queue.consumerQueue}")
    public void consumer(RabbitMQModel<?> data) {
        rabbitService.callback(data);
    }
}
