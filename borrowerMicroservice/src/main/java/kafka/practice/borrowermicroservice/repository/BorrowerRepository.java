package kafka.practice.borrowermicroservice.repository;

import kafka.practice.borrowermicroservice.entity.Borrower;
import reactor.core.publisher.Mono;

public interface BorrowerRepository {

    public Mono<Boolean> save(Borrower borrower);

}
