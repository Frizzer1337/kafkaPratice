package kafka.practice.collectormicroservice.controller;

import kafka.practice.api.entity.Collector;
import kafka.practice.collectormicroservice.service.impl.CollectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/collector")
public class CollectorController {

  private CollectorServiceImpl collectorService;

  @Autowired
  CollectorController(CollectorServiceImpl collectorService) {
    this.collectorService = collectorService;
  }

  @PostMapping("/register")
  public ResponseEntity<Mono<Boolean>> register(@RequestBody Collector collector) {
    return ResponseEntity.ok(collectorService.save(collector));
  }

  @PostMapping("/takeCreditInWork/{collectorCreditId}/{collectorId}")
  public ResponseEntity<Mono<Boolean>> takeCreditInWork(
      @PathVariable("collectorCreditId") String collectorCreditId,
      @PathVariable("collectorId") String collectorId) {
    return ResponseEntity.ok(collectorService.takeCreditInWork(collectorCreditId, collectorId));
  }
}
