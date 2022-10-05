package com.frizzer.kafka.kafkapractice.repository;

import com.frizzer.kafka.kafkapractice.entity.Borrower;
import reactor.core.publisher.Mono;

public interface BorrowerRepository {

    public Mono<Boolean> save(Borrower borrower);

}
