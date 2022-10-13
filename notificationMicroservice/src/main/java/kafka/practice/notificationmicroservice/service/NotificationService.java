package kafka.practice.notificationmicroservice.service;

import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.CreditCheckEvent;
import kafka.practice.api.entity.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Service
public class NotificationService {

    Logger log = LoggerFactory.getLogger(NotificationService.class);
    private KafkaReceiver<String, Credit> kafkaConsumer;
    private KafkaReceiver<String, CreditCheckEvent> kafkaEventConsumer;
    private KafkaReceiver<String, PaymentEvent> kafkaPaymentEventConsumer;

    public NotificationService(KafkaReceiver<String, Credit> kafkaConsumer, KafkaReceiver<String, CreditCheckEvent> kafkaEventConsumer, KafkaReceiver<String, PaymentEvent> kafkaPaymentEventConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        this.kafkaEventConsumer = kafkaEventConsumer;
        this.kafkaPaymentEventConsumer = kafkaPaymentEventConsumer;
    }

    @EventListener(ApplicationStartedEvent.class)
    public Mono<Void> kafkaReceiving() {
        return kafkaConsumer.receive()
                .doOnNext(
                          x -> log.info("Credit {} was send to approve offset: {}", x.value().getClass(), x.offset())
                ).then(Mono.empty());
    }

    @EventListener(ApplicationStartedEvent.class)
    public Mono<Void> kafkaEventReceiving() {
        return kafkaEventConsumer.receive()
                .doOnNext(
                        x -> log.info("Credit {} was checked by algorithm offset: {}", x.value().getClass(), x.offset())
                ).then(Mono.empty());
    }

    @EventListener(ApplicationStartedEvent.class)
    public Mono<Void> kafkaReceivingPayment() {
        return kafkaPaymentEventConsumer.receive()
                .doOnNext(x -> log.info("New payment {} offset: {}",x.value().getClass(), x.offset()))
                .then(Mono.empty());
    }


}
