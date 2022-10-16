package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.Payment;
import kafka.practice.paymentmicroservice.repository.PaymentRepository;
import kafka.practice.paymentmicroservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

  private PaymentRepository paymentRepository;
  private final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Autowired
  public PaymentServiceImpl(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public Mono<Boolean> save(Payment payment) {
    return paymentRepository.save(payment);
  }

}
