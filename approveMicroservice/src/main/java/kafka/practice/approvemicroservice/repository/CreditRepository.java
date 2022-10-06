package kafka.practice.approvemicroservice.repository;

import kafka.practice.approvemicroservice.entity.Credit;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    public Mono<Boolean> save(Credit credit);

}
