package com.frizzer.kafka.kafkapractice.service.impl;

import com.frizzer.kafka.kafkapractice.entity.Credit;
import com.frizzer.kafka.kafkapractice.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl {

    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository){
        this.creditRepository = creditRepository;
    }

    public Mono<Boolean> takeCredit(Credit credit){
            return creditRepository.save(credit);
    }



}
