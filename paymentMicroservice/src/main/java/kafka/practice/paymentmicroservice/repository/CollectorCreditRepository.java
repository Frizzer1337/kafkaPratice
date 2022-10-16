package kafka.practice.paymentmicroservice.repository;

import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.api.entity.Credit;
import reactor.core.publisher.Mono;

public interface CollectorCreditRepository {

    public Mono<Boolean> save(CollectorCredit collectorCredit);

}
