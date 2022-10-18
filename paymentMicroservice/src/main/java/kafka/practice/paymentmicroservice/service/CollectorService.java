package kafka.practice.paymentmicroservice.service;

import kafka.practice.api.entity.CollectorCredit;
import reactor.core.publisher.Mono;

public interface CollectorService {

  Mono<Boolean> save(CollectorCredit collectorCredit);
}
