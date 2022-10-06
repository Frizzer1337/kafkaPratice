package kafka.practice.approvemicroservice.controller;

import kafka.practice.approvemicroservice.entity.Borrower;
import kafka.practice.approvemicroservice.entity.Credit;
import kafka.practice.approvemicroservice.service.impl.BorrowerServiceImpl;
import kafka.practice.approvemicroservice.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/approve")
public class ApproveController {

    private BorrowerServiceImpl borrowerService;
    private CreditServiceImpl creditService;

    @Autowired
    ApproveController(BorrowerServiceImpl borrowerService, CreditServiceImpl creditService) {
        this.borrowerService = borrowerService;
        this.creditService = creditService;
    }


    @KafkaListener(topics = "CREDIT_APPROVE", groupId = "approveConsumers")
    public Mono<Borrower> notificationListener(Credit credit) {
        System.out.println(credit);
        return borrowerService.findBorrowerById(credit.getBorrowerId())
               .flatMap(x->creditService.approve(credit,x)).then(Mono.empty());
    }

    @GetMapping("/getUserByCredit/{id}")
    public ResponseEntity<Mono<Borrower>> getUserByCredit(@PathVariable String id){
        return ResponseEntity.ok(borrowerService.findBorrowerById(id).doOnNext(System.out::println));
    }



}
