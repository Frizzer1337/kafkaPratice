package kafka.practice.borrowermicroservice.service.impl;

import kafka.practice.api.entity.Borrower;
import kafka.practice.borrowermicroservice.repository.BorrowerRepository;
import kafka.practice.borrowermicroservice.service.BorrowerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BorrowerServiceImpl implements BorrowerService {

  private BorrowerRepository borrowerRepository;

  public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
    this.borrowerRepository = borrowerRepository;
  }

  @Override
  public Mono<Boolean> register(Borrower borrower) {
    return borrowerRepository.save(borrower);
  }
}
