package com.frizzer.kafka.kafkapractice.configuration;

import com.frizzer.kafka.kafkapractice.entity.Borrower;
import com.frizzer.kafka.kafkapractice.repository.BorrowerRepository;
import com.frizzer.kafka.kafkapractice.repository.MongoBorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class BorrowerRepositoryConfiguration {

    private BorrowerMongoConfiguration borrowerMongoConfiguration;

    @Autowired
    public BorrowerRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration){
        this.borrowerMongoConfiguration = borrowerMongoConfiguration;
    }

    @Bean
    public BorrowerRepository borrowerRepository(){
        return new MongoBorrowerRepository(borrowerMongoConfiguration.database().getCollection("borrower",Borrower.class));
    }
}
