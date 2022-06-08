package com.bnz.bdlpc.rabbitmq;

import com.bnz.bdlpc.modules.RabbitCallback;
import com.bnz.shared.models.RabbitMQModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private RabbitCallback rabbitCallback = new RabbitCallback();

    @RabbitListener(queues = "${bnz.rabbitmq.queue.consumerQueue}")
    public void consumer(RabbitMQModel<?> data) {
        rabbitCallback.callback(data);
    }
}
