package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.*;
import kafka.practice.paymentmicroservice.repository.CreditRepository;
import kafka.practice.paymentmicroservice.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  private CreditRepository creditRepository;
  private PaymentServiceImpl paymentService;
  private CollectorServiceImpl collectorService;
  private ReactiveKafkaProducerTemplate<String, PaymentEvent> kafkaTemplate;
  private ReactiveKafkaProducerTemplate<String, CollectorEvent> kafkaCollectorTemplate;
  private ReactiveKafkaProducerTemplate<String, CreditPayedEvent> kafkaCreditPayedTemplate;
  private final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Autowired
  public CreditServiceImpl(
      CreditRepository creditRepository,
      PaymentServiceImpl paymentService,
      CollectorServiceImpl collectorService,
      ReactiveKafkaProducerTemplate<String, PaymentEvent> kafkaTemplate,
      ReactiveKafkaProducerTemplate<String, CollectorEvent> kafkaCollectorTemplate,
      ReactiveKafkaProducerTemplate<String, CreditPayedEvent> kafkaCreditPayedTemplate) {
    this.creditRepository = creditRepository;
    this.paymentService = paymentService;
    this.collectorService = collectorService;
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaCollectorTemplate = kafkaCollectorTemplate;
    this.kafkaCreditPayedTemplate = kafkaCreditPayedTemplate;
  }

  @Override
  public Mono<Boolean> pay(Payment payment) {
    return creditRepository
        .pay(payment)
        .flatMap(
            x ->
                creditRepository
                    .checkIfCreditPayed(x)
                    .flatMap(
                        creditPayed -> {
                          if (Boolean.TRUE.equals(creditPayed)) {
                            return kafkaCreditPayedTemplate
                                .send("CREDIT_PAYED", new CreditPayedEvent(x))
                                .doOnSuccess(
                                    result ->
                                        log.info(
                                            "Credit payed event sent {} offset: {}",
                                            x,
                                            result.recordMetadata().offset()));
                          } else {
                            return kafkaTemplate
                                .send("CREDIT_PAYMENT", new PaymentEvent(x))
                                .doOnSuccess(
                                    result ->
                                        log.info(
                                            "Credit payment event sent {} offset: {}",
                                            x,
                                            result.recordMetadata().offset()));
                          }
                        }))
        .map(it -> true)
        .defaultIfEmpty(false);
  }

  @Transactional
  @Override
  public Mono<Boolean> payAndSavePayment(Payment payment) {
    return paymentService.save(payment).then(pay(payment));
  }

  @Scheduled(fixedDelay = 10000)
  @Override
  public Disposable sendPenalty() {
    log.info("Credit's were checked");
    return creditRepository.sendPenalty().subscribe();
  }

  @Scheduled(fixedDelay = 10000)
  @Override
  public Disposable changeStatusForBigPenalty() {
    return creditRepository.checkCreditToSendCollector().subscribe();
  }

  @Scheduled(fixedDelay = 10000)
  @Override
  public Disposable sendToCollectorsForBigPenalty() {
    return creditRepository
        .findCreditToSendCollector()
        .flatMap(
            x ->
                collectorService
                    .save(new CollectorCredit(x))
                    .then(
                        kafkaCollectorTemplate
                            .send("CREDIT_TO_COLLECTOR", new CollectorEvent(x))
                            .doOnNext(
                                result ->
                                    log.info(
                                        "sent {} offset: {}",
                                        x,
                                        result.recordMetadata().offset()))))
        .then(creditRepository.markCreditSendToCollector())
        .subscribe();
  }

  @Scheduled(fixedDelay = 10000)
  @Override
  public Disposable sendWarnForBigPenalty() {
    return creditRepository
        .checkCreditToWarn()
        .doOnNext(x -> log.warn("Credit have big penalty {}", x))
        .subscribe();
  }
}
