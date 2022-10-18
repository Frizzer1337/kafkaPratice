package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.paymentmicroservice.repository.CollectorCreditRepository;
import kafka.practice.paymentmicroservice.service.CollectorService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CollectorServiceImpl implements CollectorService {

  private CollectorCreditRepository collectorRepository;

  public CollectorServiceImpl(CollectorCreditRepository collectorRepository) {
    this.collectorRepository = collectorRepository;
  }

  @Override
  public Mono<Boolean> save(CollectorCredit collectorCredit) {
    return collectorRepository.save(collectorCredit);
  }
}
