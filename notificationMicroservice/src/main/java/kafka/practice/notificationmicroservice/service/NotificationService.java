package kafka.practice.notificationmicroservice.service;

import reactor.core.publisher.Mono;

public interface NotificationService {

  Mono<Void> kafkaReceiving();

  Mono<Void> kafkaEventReceiving();

  Mono<Void> kafkaReceivingPayment();

  Mono<Void> kafkaSendToCollector();

  Mono<Void> kafkaCreditPayed();
}
