package com.frizzer.kafka.kafkapractice.configuration;

import com.frizzer.kafka.kafkapractice.entity.Borrower;
import com.frizzer.kafka.kafkapractice.entity.Credit;
import com.frizzer.kafka.kafkapractice.repository.BorrowerRepository;
import com.frizzer.kafka.kafkapractice.repository.CreditRepository;
import com.frizzer.kafka.kafkapractice.repository.MongoBorrowerRepository;
import com.frizzer.kafka.kafkapractice.repository.MongoCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditRepositoryConfiguration {

    private BorrowerMongoConfiguration borrowerMongoConfiguration;

    @Autowired
    public CreditRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration){
        this.borrowerMongoConfiguration = borrowerMongoConfiguration;
    }

    @Bean
    public CreditRepository creditRepository(){
        return new MongoCreditRepository(borrowerMongoConfiguration.database().getCollection("credit", Credit.class));
    }
}
