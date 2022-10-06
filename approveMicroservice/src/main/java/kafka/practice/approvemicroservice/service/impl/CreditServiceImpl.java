package kafka.practice.approvemicroservice.service.impl;

import kafka.practice.approvemicroservice.entity.Borrower;
import kafka.practice.approvemicroservice.entity.Credit;
import kafka.practice.approvemicroservice.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl {

    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Mono<Void> approve(Credit credit, Borrower borrower) {
        final double SOCIAL_CREDIT_MODIFIER = 0.15;
        final double SALARY_MODIFIER = 0.10;
        return Mono.just(credit)
                .flatMap(it -> {
                            double creditRate = borrower.getSocialCredit() * SOCIAL_CREDIT_MODIFIER
                                    + borrower.getSalary() * SALARY_MODIFIER - credit.getCreditBalance();
                            System.out.println(creditRate);
                            return Mono.empty();
                        }
                );
    }


}
