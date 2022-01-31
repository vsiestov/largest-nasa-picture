package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    @Bean
    Exchange requestExchange() {
        return new TopicExchange("picture-request-topic");
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue queue() {
        return new Queue("siestov");
    }

    @Bean
    Binding bindingAll() {
        return BindingBuilder.bind(queue()).to(requestExchange()).with("all").noargs();
    }

    @Bean
    Binding bindingSiestov() {
        return BindingBuilder.bind(queue()).to(requestExchange()).with("siestov").noargs();
    }

    @Bean
    Binding bindingTeam() {
        return BindingBuilder.bind(queue()).to(requestExchange()).with("svydovets").noargs();
    }
}
