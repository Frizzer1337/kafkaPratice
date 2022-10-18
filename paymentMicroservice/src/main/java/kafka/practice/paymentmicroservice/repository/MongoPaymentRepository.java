package kafka.practice.paymentmicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Mono;

public class MongoPaymentRepository implements PaymentRepository {

  MongoCollection<Payment> collection;

  public MongoPaymentRepository(MongoCollection<Payment> collection) {
    this.collection = collection;
  }

  @Override
  public Mono<Boolean> save(Payment payment) {
    return Mono.from(collection.insertOne(payment)).map(x -> true).defaultIfEmpty(false);
  }
}
