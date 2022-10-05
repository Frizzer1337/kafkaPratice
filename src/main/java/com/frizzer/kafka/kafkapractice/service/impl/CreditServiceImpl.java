package com.frizzer.kafka.kafkapractice.service.impl;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import com.frizzer.kafka.kafkapractice.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl {

    private CreditRepository creditRepository;
    private KafkaTemplate<String,Object> kafkaTemplate;


    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,KafkaTemplate<String, Object> kafkaTemplate) {
        this.creditRepository = creditRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Boolean> takeCredit(Credit credit) {
        return creditRepository.save(credit).flatMap(x ->
                Mono.just(kafkaTemplate.send("CREDIT_APPROVE", credit))
                        .thenReturn(x)
        );
    }


}
