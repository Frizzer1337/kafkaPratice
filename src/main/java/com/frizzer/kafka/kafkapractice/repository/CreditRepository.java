package com.frizzer.kafka.kafkapractice.repository;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    public Mono<Boolean> save(Credit credit);
}
