package kafka.practice.collectormicroservice.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import kafka.practice.api.entity.CollectorCredit;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoCollectorCreditRepository implements CollectorCreditRepository {

  private MongoCollection<CollectorCredit> collection;

  public MongoCollectorCreditRepository(MongoCollection<CollectorCredit> collection) {
    this.collection = collection;
  }

  @Override
  public Mono<Boolean> save(CollectorCredit collectorCredit) {
    return Mono.from(collection.insertOne(collectorCredit)).map(x -> true).defaultIfEmpty(false);
  }

  @Override
  public Mono<Boolean> takeCreditInWork(String collectorCreditId, String collectorId) {
    return Mono.from(
            collection.updateOne(eq("_id", collectorCreditId), set("collectorId", collectorId)))
        .map(x -> true)
        .defaultIfEmpty(false);
  }

  @Override
  public Mono<Boolean> changeLastCallDate(Flux<CollectorCredit> credits) {
    return Mono.from(
            credits.flatMap(
                credit ->
                    collection.updateOne(
                        eq("_id", credit.getId()),
                        set("lastCallDate", LocalDateTime.now().toString()))))
        .map(x -> true)
        .defaultIfEmpty(false);
  }

  @Override
  public Flux<CollectorCredit> checkCreditToCall() {
    return Flux.from(
        collection.aggregate(
            Arrays.asList(
                new Document(
                    "$lookup",
                    new Document("from", "collector")
                        .append("localField", "collectorId")
                        .append("foreignField", "_id")
                        .append("as", "result")),
                new Document(
                    "$match",
                    new Document(
                        "$and",
                        Arrays.asList(
                            new Document("result.collectorActivityStatus", "IN_WORK"),
                            new Document(
                                "$expr",
                                new Document(
                                    "$lt",
                                    Arrays.asList(
                                        "$lastCallDate", LocalDateTime.now().minusDays(1).toString())))))))));
  }
}
