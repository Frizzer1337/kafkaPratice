package kafka.practice.notificationmicroservice.service;

import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.CreditCheckEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

    Logger log = LoggerFactory.getLogger(NotificationService.class);
    private ReactiveKafkaConsumerTemplate<String, Credit> kafkaConsumer;
    private ReactiveKafkaConsumerTemplate<String, CreditCheckEvent> kafkaEventConsumer;

    public NotificationService(ReactiveKafkaConsumerTemplate<String, Credit> kafkaConsumer,ReactiveKafkaConsumerTemplate<String,CreditCheckEvent> kafkaEventConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        this.kafkaEventConsumer = kafkaEventConsumer;
    }

    @EventListener(ApplicationStartedEvent.class)
    public Mono<Void> kafkaReceiving() {
        return kafkaConsumer.receiveAutoAck()
                .doOnNext(x -> log.info("sent {} offset: {}", x.value(), x.offset()))
                .then(Mono.empty());
    }

    @EventListener(ApplicationStartedEvent.class)
    public Mono<Void> kafkaReceivingEvent() {
        return kafkaEventConsumer.receiveAutoAck()
                .doOnNext(x -> log.info("sent {} offset: {}", x.value(), x.offset()))
                .then(Mono.empty());
    }


}
