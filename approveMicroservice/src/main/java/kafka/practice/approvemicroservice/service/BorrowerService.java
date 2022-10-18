package kafka.practice.approvemicroservice.service;

import kafka.practice.api.entity.Borrower;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface BorrowerService {

  public Mono<Borrower> findBorrowerById(String borrowerId);
}
