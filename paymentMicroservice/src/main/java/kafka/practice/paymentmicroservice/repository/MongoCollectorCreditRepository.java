package kafka.practice.paymentmicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.CollectorCredit;
import reactor.core.publisher.Mono;

public class MongoCollectorCreditRepository implements CollectorCreditRepository {

  private MongoCollection<CollectorCredit> collection;

  public MongoCollectorCreditRepository(MongoCollection<CollectorCredit> collection) {
    this.collection = collection;
  }

  @Override
  public Mono<Boolean> save(CollectorCredit collectorCredit) {
    return Mono.from(collection.insertOne(collectorCredit)).map(x -> true).defaultIfEmpty(false);
  }
}
