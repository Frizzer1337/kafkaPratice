package kafka.practice.borrowermicroservice.repository;

import kafka.practice.api.entity.Credit;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    public Mono<Boolean> save(Credit credit);

}
