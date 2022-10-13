package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.Borrower;
import kafka.practice.paymentmicroservice.repository.BorrowerRepository;
import kafka.practice.paymentmicroservice.service.BorrowerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Mono<Boolean> register(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }


}

