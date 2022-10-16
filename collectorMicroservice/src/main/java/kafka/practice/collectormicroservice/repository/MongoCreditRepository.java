package kafka.practice.collectormicroservice.repository;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.Payment;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
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
        collection.findOneAndUpdate(
            eq("_id", payment.getCreditId()),
            Updates.combine(
                inc("creditBalance", -1 * payment.getPayment()),
                set("lastPaymentDate", LocalDateTime.now().toString())),
            new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)));
  }

  @Override
  public Mono<Boolean> sendPenalty() {
    return Mono.from(
            collection.updateMany(
                lt("lastPaymentDate", LocalDateTime.now().minusDays(1).toString()),
                inc("penalty", 50)))
        .map(x -> true)
        .defaultIfEmpty(false);
  }

  @Override
  public Flux<UpdateResult> checkCreditToSendCollector() {
    return Flux.from(
        collection.updateMany(
            new Document(
                "$and",
                Arrays.asList(
                    new Document(
                        "$expr",
                        new Document(
                            "$gt",
                            Arrays.asList(
                                new Document(
                                    "$divide", Arrays.asList("$penalty", "$creditBalance")),
                                0.5d))),
                    new Document(
                        "$expr",
                        new Document("$eq", Arrays.asList("$creditStatus", "APPROVED"))))),
            Arrays.asList(new Document("$set", new Document("creditStatus", "NEED_COLLECTOR")))));
  }

  @Override
  public Flux<Credit> findCreditToSendCollector() {
    return Flux.from(collection.find(eq("creditStatus", "NEED_COLLECTOR")));
  }

  @Override
  public Mono<Boolean> markCreditSendToCollector() {
    return Mono.from(
            collection.updateMany(
                eq("creditStatus", "NEED_COLLECTOR"), set("creditStatus", "SEND_TO_COLLECTOR")))
        .map(x -> true)
        .defaultIfEmpty(false);
  }

  @Override
  public Flux<Credit> checkCreditToWarn() {
    return Flux.from(
        collection.aggregate(
            Arrays.asList(
                new Document(
                    "$match",
                    new Document(
                        "$expr",
                        new Document(
                            "$gt",
                            Arrays.asList(
                                new Document(
                                    "$divide", Arrays.asList("$penalty", "$creditBalance")),
                                0.3d)))),
                new Document(
                    "$match",
                    new Document(
                        "$expr",
                        new Document(
                            "$lt",
                            Arrays.asList(
                                new Document(
                                    "$divide", Arrays.asList("$penalty", "$creditBalance")),
                                0.5d)))))));
  }
}
