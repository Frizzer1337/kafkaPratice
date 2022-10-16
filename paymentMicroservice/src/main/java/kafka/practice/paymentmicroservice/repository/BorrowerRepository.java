package kafka.practice.paymentmicroservice.repository;

import kafka.practice.api.entity.Borrower;
import reactor.core.publisher.Mono;

public interface BorrowerRepository {

  Mono<Boolean> save(Borrower borrower);
}
