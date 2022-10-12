package kafka.practice.approvemicroservice.repository;

import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.CreditStatus;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    public Mono<Boolean> save(Credit credit);
    public Mono<Credit> changeStatus(Credit credit, CreditStatus creditStatus);

}
