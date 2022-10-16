package kafka.practice.collectormicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Collector;
import reactor.core.publisher.Mono;

public class MongoCollectorRepository implements CollectorRepository {

  private MongoCollection<Collector> collection;

  public MongoCollectorRepository(MongoCollection<Collector> collection) {
    this.collection = collection;
  }

  @Override
  public Mono<Boolean> save(Collector collector) {
    return Mono.from(collection.insertOne(collector)).map(x -> true).defaultIfEmpty(false);
  }
}
