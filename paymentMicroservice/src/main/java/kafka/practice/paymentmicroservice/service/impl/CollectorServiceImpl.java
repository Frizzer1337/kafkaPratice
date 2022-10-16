package kafka.practice.paymentmicroservice.service.impl;

import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.paymentmicroservice.repository.CollectorCreditRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CollectorServiceImpl {

    private CollectorCreditRepository collectorRepository;

    public CollectorServiceImpl(CollectorCreditRepository collectorRepository) {
        this.collectorRepository = collectorRepository;
    }

    public Mono<Boolean> save(CollectorCredit collectorCredit){
        return collectorRepository.save(collectorCredit);
    }
}
