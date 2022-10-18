package kafka.practice.paymentmicroservice.service;

import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {
  Mono<Boolean> save(Payment payment);
}
