package kafka.practice.borrowermicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Borrower;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

public class MongoBorrowerRepository implements BorrowerRepository {

  MongoCollection<Borrower> collection;

  public MongoBorrowerRepository(MongoCollection<Borrower> collection) {
    this.collection = collection;
  }

  public Mono<Boolean> save(Borrower borrower) {
    return Mono.from(collection.insertOne(borrower)).map(x -> true).defaultIfEmpty(false);
  }

  public Mono<Borrower> findBorrowerById(String borrowerId) {
    return Mono.from(collection.find(eq("_id", borrowerId)).first());
  }
}
