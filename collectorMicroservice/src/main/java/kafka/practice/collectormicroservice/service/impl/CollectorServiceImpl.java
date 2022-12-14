package kafka.practice.collectormicroservice.service.impl;

import kafka.practice.api.entity.Collector;
import kafka.practice.collectormicroservice.repository.CollectorCreditRepository;
import kafka.practice.collectormicroservice.repository.CollectorRepository;
import kafka.practice.collectormicroservice.service.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public class CollectorServiceImpl implements CollectorService {

  private CollectorCreditRepository collectorCreditRepository;
  private CollectorRepository collectorRepository;
  private Logger logger = LoggerFactory.getLogger(CollectorServiceImpl.class);

  public CollectorServiceImpl(
      CollectorCreditRepository collectorCreditRepository,
      CollectorRepository collectorRepository) {
    this.collectorCreditRepository = collectorCreditRepository;
    this.collectorRepository = collectorRepository;
  }

  @Override
  public Mono<Boolean> save(Collector collector) {
    return collectorRepository.save(collector);
  }

  @Override
  public Mono<Boolean> takeCreditInWork(String collectorCreditId, String collectorId) {
    return collectorCreditRepository.takeCreditInWork(collectorCreditId, collectorId);
  }

  @Override
  @Scheduled(fixedRate = 10000)
  public Disposable checkCreditToCall() {
    return collectorCreditRepository
        .changeLastCallDate(
            collectorCreditRepository
                .checkCreditToCall()
                .doOnNext(
                    x ->
                        logger.info(
                            "Collector with id {} called borrower about credit with id {}",
                            x.getCollectorId(),
                            x.getCreditId())))
        .subscribe();
  }
}
