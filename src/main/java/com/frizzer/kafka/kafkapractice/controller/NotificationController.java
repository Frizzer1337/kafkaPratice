package com.frizzer.kafka.kafkapractice.controller;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import com.frizzer.kafka.kafkapractice.service.impl.BorrowerServiceImpl;
import com.frizzer.kafka.kafkapractice.service.impl.CreditServiceImpl;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private BorrowerServiceImpl borrowerService;
    private CreditServiceImpl creditService;
    private KafkaTemplate<String, Object> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationController(BorrowerServiceImpl borrowerService, CreditServiceImpl creditService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.borrowerService = borrowerService;
        this.creditService = creditService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "CREDIT_APPROVE", groupId = "myConsumers")
    public void notificationListener(Credit credit) {
        logger.info("Borrower take credit" + credit);
    }



}
