package kafka.practice.approvemicroservice.repository;

import kafka.practice.approvemicroservice.entity.Borrower;
import reactor.core.publisher.Mono;

public interface BorrowerRepository {

    public Mono<Boolean> save(Borrower borrower);

    public Mono<Borrower> findBorrowerById(String borrowerId);

}
