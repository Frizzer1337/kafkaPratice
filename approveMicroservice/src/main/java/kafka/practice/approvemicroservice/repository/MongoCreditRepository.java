package kafka.practice.approvemicroservice.repository;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.CreditStatus;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

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

    @Override
    public Mono<Credit> changeStatus(Credit credit, CreditStatus creditStatus){
        return Mono
                .from(collection.findOneAndUpdate(eq("_id",credit.getId()),set("creditStatus",creditStatus)
                        ,new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)));
    }
}
