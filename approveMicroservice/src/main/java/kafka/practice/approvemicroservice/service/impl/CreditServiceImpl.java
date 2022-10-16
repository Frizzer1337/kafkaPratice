package kafka.practice.approvemicroservice.service.impl;

import kafka.practice.api.entity.Borrower;
import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.CreditCheckEvent;
import kafka.practice.api.entity.CreditStatus;
import kafka.practice.approvemicroservice.repository.CreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl {

  private CreditRepository creditRepository;
  private ReactiveKafkaConsumerTemplate<String, Credit> kafkaConsumer;
  private BorrowerServiceImpl borrowerService;
  private ReactiveKafkaProducerTemplate<String, CreditCheckEvent> kafkaTemplate;
  private final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Autowired
  public CreditServiceImpl(
      CreditRepository creditRepository,
      ReactiveKafkaConsumerTemplate<String, Credit> kafkaConsumer,
      BorrowerServiceImpl borrowerService,
      ReactiveKafkaProducerTemplate<String, CreditCheckEvent> kafkaTemplate) {
    this.creditRepository = creditRepository;
    this.kafkaConsumer = kafkaConsumer;
    this.borrowerService = borrowerService;
    this.kafkaTemplate = kafkaTemplate;
  }

  @EventListener(ApplicationStartedEvent.class)
  public Flux<Credit> kafkaReceiving() {
    return kafkaConsumer
        .receiveAutoAck()
        .flatMap(
            x ->
                borrowerService
                    .findBorrowerById(x.value().getBorrowerId())
                    .flatMap(borrower -> approve(x.value(), borrower)))
        .flatMap(
            credit ->
                kafkaTemplate
                    .send("CREDIT_CHECKED", new CreditCheckEvent(credit))
                    .doOnSuccess(
                        result ->
                            log.info(
                                "sent {} offset: {}", credit, result.recordMetadata().offset()))
                    .thenReturn(credit));
  }

  public Mono<Credit> approve(Credit credit, Borrower borrower) {
    final double SOCIAL_CREDIT_MODIFIER = 1.5;
    final double SALARY_MODIFIER = 10;
    final double CREDIT_BALANCE_MODIFIER = 0.2;
    return Mono.just(credit)
        .flatMap(
            it -> {
              double creditRate =
                  borrower.getSocialCredit() * SOCIAL_CREDIT_MODIFIER
                      + borrower.getSalary() * SALARY_MODIFIER
                      - credit.getCreditBalance() * CREDIT_BALANCE_MODIFIER;
              return approveByCreditRate(creditRate, credit);
            });
  }

  public Mono<Credit> approveByCreditRate(double creditRate, Credit credit) {
    double approveRate = 120;
    double humanApproveRate = 100;
    CreditStatus creditStatus = CreditStatus.NOT_APPROVED;

    if (creditRate > approveRate) {
      creditStatus = CreditStatus.APPROVED;
    } else if (creditRate > humanApproveRate) {
      creditStatus = CreditStatus.NEED_HUMAN_APPROVE;
    }

    return creditRepository.changeStatus(credit, creditStatus);
  }
}
