package com.bnz.bdlpc.modules;

import com.bnz.bdlpc.rabbitmq.Publisher;
import com.bnz.shared.constants.RabbitMQConstants;
import com.bnz.shared.models.QuestionResponse;
import com.bnz.shared.models.RabbitMQModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class RabbitCallback {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private Publisher publisher;

    public void callback(RabbitMQModel<?> data) {
        try {
            log.info("[+] Callback entered at "+ new Date());
            log.warn("[!] Starting to process"+data);
            TimeUnit.SECONDS.sleep(2);
            ObjectMapper mapper = new ObjectMapper();
            QuestionResponse question = mapper.convertValue(data.getData(), new TypeReference<>() {});
            System.out.println(question);
            question.setScore(10);
            question.setResponse("Successfully compiled and tested!");
            publisher.send(RabbitMQConstants.ROUTING_KEY_RESPONSE, new RabbitMQModel("response-data", "bdlpc-service", question));
            log.info("[+] Callback finished at "+ new Date());
        }catch (Exception e) {
            System.out.println(e);
            log.error("Invalid input type.");
        }

    }
}