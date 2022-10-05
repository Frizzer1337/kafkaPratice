package com.frizzer.kafka.kafkapractice.service.impl;

import com.frizzer.kafka.kafkapractice.entity.Borrower;
import com.frizzer.kafka.kafkapractice.repository.BorrowerRepository;
import com.frizzer.kafka.kafkapractice.repository.MongoBorrowerRepository;
import com.frizzer.kafka.kafkapractice.service.BorrowerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BorrowerServiceImpl implements BorrowerService{

    private BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository){
        this.borrowerRepository = borrowerRepository;
    }

    public Mono<Boolean> register(Borrower borrower){
        return borrowerRepository.save(borrower);
    }

}
