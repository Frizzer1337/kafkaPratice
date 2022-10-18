package kafka.practice.paymentmicroservice.service;

import kafka.practice.api.entity.Payment;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public interface CreditService {
  Mono<Boolean> pay(Payment payment);

  Mono<Boolean> payAndSavePayment(Payment payment);

  Disposable sendPenalty();

  Disposable changeStatusForBigPenalty();

  Disposable sendToCollectorsForBigPenalty();

  Disposable sendWarnForBigPenalty();
}
