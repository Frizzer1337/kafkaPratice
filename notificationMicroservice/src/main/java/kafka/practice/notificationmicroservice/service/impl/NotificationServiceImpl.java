package kafka.practice.notificationmicroservice.service.impl;

import kafka.practice.api.entity.*;
import kafka.practice.notificationmicroservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Service
public class NotificationServiceImpl implements NotificationService {

  Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
  private KafkaReceiver<String, Credit> kafkaConsumer;
  private KafkaReceiver<String, CreditCheckEvent> kafkaEventConsumer;
  private KafkaReceiver<String, PaymentEvent> kafkaPaymentEventConsumer;
  private KafkaReceiver<String, CollectorEvent> kafkaCollectorEventConsumer;
  private KafkaReceiver<String, CreditPayedEvent> kafkaPayedEventConsumer;

  public NotificationServiceImpl(
      KafkaReceiver<String, Credit> kafkaConsumer,
      KafkaReceiver<String, CreditCheckEvent> kafkaEventConsumer,
      KafkaReceiver<String, PaymentEvent> kafkaPaymentEventConsumer,
      KafkaReceiver<String, CollectorEvent> kafkaCollectorEventConsumer,
      KafkaReceiver<String, CreditPayedEvent> kafkaPayedEventConsumer) {
    this.kafkaConsumer = kafkaConsumer;
    this.kafkaEventConsumer = kafkaEventConsumer;
    this.kafkaPaymentEventConsumer = kafkaPaymentEventConsumer;
    this.kafkaCollectorEventConsumer = kafkaCollectorEventConsumer;
    this.kafkaPayedEventConsumer = kafkaPayedEventConsumer;
  }

  @Override
  @EventListener(ApplicationStartedEvent.class)
  public Mono<Void> kafkaReceiving() {
    return kafkaConsumer
        .receive()
        .doOnNext(
            x ->
                log.info(
                    "Credit {} was send to approve offset: {}", x.value().getClass(), x.offset()))
        .then(Mono.empty());
  }

  @Override
  @EventListener(ApplicationStartedEvent.class)
  public Mono<Void> kafkaEventReceiving() {
    return kafkaEventConsumer
        .receive()
        .doOnNext(
            x ->
                log.info(
                    "Credit {} was checked by algorithm offset: {}",
                    x.value().getClass(),
                    x.offset()))
        .then(Mono.empty());
  }

  @Override
  @EventListener(ApplicationStartedEvent.class)
  public Mono<Void> kafkaReceivingPayment() {
    return kafkaPaymentEventConsumer
        .receive()
        .doOnNext(x -> log.info("New payment {} offset: {}", x.value().getClass(), x.offset()))
        .then(Mono.empty());
  }

  @Override
  @EventListener(ApplicationStartedEvent.class)
  public Mono<Void> kafkaSendToCollector() {
    return kafkaCollectorEventConsumer
        .receive()
        .doOnNext(
            x ->
                log.info(
                    "Credit was send to collectors {} offset: {}",
                    x.value().getClass(),
                    x.offset()))
        .then(Mono.empty());
  }

  @Override
  @EventListener(ApplicationStartedEvent.class)
  public Mono<Void> kafkaCreditPayed() {
    return kafkaPayedEventConsumer
        .receive()
        .doOnNext(
            x -> log.info("Credit {} was payed  offset: {}", x.value().getClass(), x.offset()))
        .then(Mono.empty());
  }
}
