package kafka.practice.collectormicroservice.repository;

import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

  Mono<Boolean> save(Payment payment);

}
