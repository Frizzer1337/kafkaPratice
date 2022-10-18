package kafka.practice.borrowermicroservice.service.impl;

import kafka.practice.api.entity.Credit;
import kafka.practice.borrowermicroservice.repository.CreditRepository;
import kafka.practice.borrowermicroservice.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  private CreditRepository creditRepository;
  private ReactiveKafkaProducerTemplate<String, Credit> kafkaTemplate;
  private final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Autowired
  public CreditServiceImpl(
      CreditRepository creditRepository,
      ReactiveKafkaProducerTemplate<String, Credit> kafkaTemplate) {
    this.creditRepository = creditRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public Mono<Boolean> takeCredit(Credit credit) {
    return creditRepository
        .save(credit)
        .flatMap(
            x ->
                kafkaTemplate
                    .send("CREDIT_APPROVE", credit)
                    .doOnSuccess(
                        result ->
                            log.info(
                                "sent {} offset: {}", credit, result.recordMetadata().offset()))
                    .thenReturn(x));
  }
}
