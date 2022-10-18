package kafka.practice.borrowermicroservice.service;

import kafka.practice.api.entity.Borrower;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface BorrowerService {
  Mono<Boolean> register(Borrower borrower);
}
