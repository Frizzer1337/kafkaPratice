package kafka.practice.paymentmicroservice.controller;

import kafka.practice.api.entity.Payment;
import kafka.practice.paymentmicroservice.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment")
public class PaymentController {

  private CreditServiceImpl creditService;

  @Autowired
  PaymentController(CreditServiceImpl creditService) {
    this.creditService = creditService;
  }

  @PostMapping("/pay")
  public ResponseEntity<Mono<Boolean>> register(@RequestBody Payment payment) {
    return ResponseEntity.ok(creditService.payAndSavePayment(payment));
  }
}
