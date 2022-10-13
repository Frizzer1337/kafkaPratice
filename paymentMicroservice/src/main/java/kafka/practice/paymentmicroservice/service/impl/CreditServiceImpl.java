package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.Credit;
import kafka.practice.api.entity.Payment;
import kafka.practice.api.entity.PaymentEvent;
import kafka.practice.paymentmicroservice.repository.CreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl {

    private CreditRepository creditRepository;
    private ReactiveKafkaProducerTemplate<String, PaymentEvent> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);


    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, ReactiveKafkaProducerTemplate<String, PaymentEvent> kafkaTemplate) {
        this.creditRepository = creditRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Boolean> pay(Payment payment) {
        return creditRepository.pay(payment)
                .flatMap(x -> kafkaTemplate.send("CREDIT_PAYMENT", new PaymentEvent(x))
                        .doOnSuccess(result ->
                                log.info("sent {} offset: {}", x, result.recordMetadata().offset()))
                        ).map(it ->true).defaultIfEmpty(false);
    }


}
