package com.example.demo;

import com.example.demo.service.NasaService;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    private NasaService service;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    @SneakyThrows
    private void postConstruct() {

//        System.out.println(service.getPicture("15", "FHAZ"));
    }
}
