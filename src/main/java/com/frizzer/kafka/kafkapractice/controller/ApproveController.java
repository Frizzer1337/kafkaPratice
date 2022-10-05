package com.frizzer.kafka.kafkapractice.controller;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApproveController {

    @KafkaListener(topics = "CREDIT_APPROVE", groupId = "myConsumers")
    public void notificationListener(Credit credit) {
        System.out.println(credit);
    }


}
