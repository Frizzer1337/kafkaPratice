package kafka.practice.borrowermicroservice.service;

import kafka.practice.api.entity.Credit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface CreditService {
  Mono<Boolean> takeCredit(Credit credit);
}
