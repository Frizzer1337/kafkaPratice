package kafka.pratice.notificationmicroservice.service;

import kafka.pratice.notificationmicroservice.entity.Credit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "CREDIT_APPROVE", groupId = "myConsumers")
    public void notificationListener(Credit credit) {
        logger.info("Borrower take credit" + credit);
    }



}
