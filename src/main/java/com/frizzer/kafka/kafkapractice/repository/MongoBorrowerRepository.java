package com.frizzer.kafka.kafkapractice.repository;

import com.frizzer.kafka.kafkapractice.entity.Borrower;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.apache.kafka.common.protocol.types.Field;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public class MongoBorrowerRepository implements BorrowerRepository {

    MongoCollection<Borrower> collection;

    public MongoBorrowerRepository(MongoCollection<Borrower> collection) {
        this.collection = collection;
    }

    public Mono<Boolean> save(Borrower borrower) {
        System.out.println(borrower);
        return Mono.from(collection.insertOne(borrower))
                .map(x->true)
                .defaultIfEmpty(false);
    }

}
