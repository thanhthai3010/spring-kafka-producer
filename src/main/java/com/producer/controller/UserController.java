package com.producer.controller;

import com.producer.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final KafkaTemplate<String, UserModel> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String TOPIC;

    @Autowired
    public UserController(KafkaTemplate<String, UserModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send( TOPIC, new UserModel( name, "Technology" ) );

        return "Published successfully";
    }

}
