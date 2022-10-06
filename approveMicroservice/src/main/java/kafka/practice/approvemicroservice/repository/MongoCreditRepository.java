package kafka.practice.approvemicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.approvemicroservice.entity.Credit;
import reactor.core.publisher.Mono;

public class MongoCreditRepository implements CreditRepository {

    MongoCollection<Credit> collection;

    public MongoCreditRepository(MongoCollection<Credit> collection) {
        this.collection = collection;
    }

    @Override
    public Mono<Boolean> save(Credit credit) {
        return Mono.from(collection.insertOne(credit))
                .map(x -> true)
                .defaultIfEmpty(false);
    }
}
