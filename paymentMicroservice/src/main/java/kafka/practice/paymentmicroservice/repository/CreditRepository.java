package kafka.practice.paymentmicroservice.repository;

import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    Mono<Credit> pay(Payment payment);
}
