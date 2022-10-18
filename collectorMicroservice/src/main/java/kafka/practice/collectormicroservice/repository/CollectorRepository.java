package kafka.practice.collectormicroservice.repository;

import kafka.practice.api.entity.Collector;
import reactor.core.publisher.Mono;

public interface CollectorRepository {

  public Mono<Boolean> save(Collector collector);
}
