package kafka.practice.borrowermicroservice.controller;

import kafka.practice.borrowermicroservice.entity.Borrower;
import kafka.practice.borrowermicroservice.entity.Credit;
import kafka.practice.borrowermicroservice.service.impl.BorrowerServiceImpl;
import kafka.practice.borrowermicroservice.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private BorrowerServiceImpl borrowerService;
    private CreditServiceImpl creditService;

    @Autowired
    BorrowerController(BorrowerServiceImpl borrowerService, CreditServiceImpl creditService) {
        this.borrowerService = borrowerService;
        this.creditService = creditService;
    }

    @PostMapping("/register")
    public ResponseEntity<Mono<Boolean>> register(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.register(borrower));
    }

    @PostMapping("/takeCredit")
    public ResponseEntity<Mono<Boolean>> takeCredit(@RequestBody Credit credit) {
        return ResponseEntity.ok(creditService.takeCredit(credit));
    }


}
