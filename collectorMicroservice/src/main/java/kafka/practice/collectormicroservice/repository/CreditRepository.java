package kafka.practice.collectormicroservice.repository;

import com.mongodb.client.result.UpdateResult;
import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    Mono<Credit> pay(Payment payment);

    Mono<Boolean> sendPenalty();

    Flux<UpdateResult> checkCreditToSendCollector();

    Flux<Credit> findCreditToSendCollector();

    Mono<Boolean> markCreditSendToCollector();

    Flux<Credit> checkCreditToWarn();
}
