package com.example.demo.service;

import com.example.demo.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Listener {
    private final NasaService service;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "siestov")
    public void listener(Request r) {
        System.out.println(r);
        var result =  service.getPicture(r.getSol(), r.getCamera());
        System.out.println(result);
        rabbitTemplate.convertAndSend("picture-result-topic", "largest", result);
    }
}
