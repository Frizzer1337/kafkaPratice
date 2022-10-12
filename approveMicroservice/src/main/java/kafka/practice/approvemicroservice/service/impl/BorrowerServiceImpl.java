package kafka.practice.approvemicroservice.service.impl;

import kafka.practice.approvemicroservice.repository.BorrowerRepository;
import kafka.practice.approvemicroservice.service.BorrowerService;
import kafka.practice.api.entity.Borrower;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository){
        this.borrowerRepository = borrowerRepository;
    }

    public Mono<Borrower> findBorrowerById(String borrowerId){
        return borrowerRepository.findBorrowerById(borrowerId);
    }

}

