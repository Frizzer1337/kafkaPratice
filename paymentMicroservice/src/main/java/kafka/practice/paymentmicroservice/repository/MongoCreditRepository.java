package kafka.practice.paymentmicroservice.repository;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.Payment;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class MongoCreditRepository implements CreditRepository {

    MongoCollection<Credit> collection;

    public MongoCreditRepository(MongoCollection<Credit> collection) {
        this.collection = collection;
    }

    @Override
    public Mono<Credit> pay(Payment payment) {
        return Mono.from(
                collection.findOneAndUpdate(eq("_id",payment.getCreditId()),
                inc("creditBalance", -1 * payment.getPayment())
                ,new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)));
    }

}
