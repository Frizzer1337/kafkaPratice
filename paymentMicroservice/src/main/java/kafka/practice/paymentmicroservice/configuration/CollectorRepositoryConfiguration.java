package kafka.practice.paymentmicroservice.configuration;

import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.api.entity.Credit;
import kafka.practice.paymentmicroservice.repository.CreditRepository;
import kafka.practice.paymentmicroservice.repository.MongoCollectorCreditRepository;
import kafka.practice.paymentmicroservice.repository.MongoCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectorRepositoryConfiguration {

  private BorrowerMongoConfiguration borrowerMongoConfiguration;

  @Autowired
  public CollectorRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration) {
    this.borrowerMongoConfiguration = borrowerMongoConfiguration;
  }

  @Bean
  public MongoCollectorCreditRepository mongoCollectorCreditRepository() {
    return new MongoCollectorCreditRepository(
        borrowerMongoConfiguration.database().getCollection("collectorCredit", CollectorCredit.class));
  }
}
