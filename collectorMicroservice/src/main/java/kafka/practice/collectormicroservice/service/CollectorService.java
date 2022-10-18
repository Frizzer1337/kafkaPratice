package kafka.practice.collectormicroservice.service;

import kafka.practice.api.entity.Collector;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public interface CollectorService {
  Mono<Boolean> save(Collector collector);

  Mono<Boolean> takeCreditInWork(String collectorCreditId, String collectorId);

  Disposable checkCreditToCall();
}
