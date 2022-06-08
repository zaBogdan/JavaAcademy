package com.bnz.services.quizzes.utils.rabbitmq;


import com.bnz.shared.constants.RabbitMQConstants;
import com.bnz.shared.models.RabbitMQModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    @Autowired
    private RabbitTemplate template;

    public boolean send(String routingKey, RabbitMQModel payload) {
        template.convertAndSend(RabbitMQConstants.EXCHANGE, routingKey, payload);
        return true;
    }
}
