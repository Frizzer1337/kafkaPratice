package kafka.practice.approvemicroservice.service;

import kafka.practice.api.entity.Borrower;
import kafka.practice.api.entity.Credit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CreditService {
  public Flux<Credit> kafkaReceiving();

  public Mono<Credit> approve(Credit credit, Borrower borrower);

  Mono<Credit> approveByCreditRate(double creditRate, Credit credit);
}
