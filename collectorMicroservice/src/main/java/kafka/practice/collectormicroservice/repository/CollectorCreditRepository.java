package kafka.practice.collectormicroservice.repository;

import kafka.practice.api.entity.CollectorCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CollectorCreditRepository {

  public Mono<Boolean> save(CollectorCredit collectorCredit);

  public Mono<Boolean> takeCreditInWork(String collectorCreditId, String collectorId);

  Mono<Boolean> changeLastCallDate(Flux<CollectorCredit> credits);

  public Flux<CollectorCredit> checkCreditToCall();
}
