package com.frizzer.kafka.kafkapractice.repository;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import com.mongodb.reactivestreams.client.MongoCollection;
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
