package kafka.practice.collectormicroservice.configuration;

import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.collectormicroservice.repository.MongoCollectorCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectorCreditRepositoryConfiguration {

  private BorrowerMongoConfiguration borrowerMongoConfiguration;

  @Autowired
  public CollectorCreditRepositoryConfiguration(
      BorrowerMongoConfiguration borrowerMongoConfiguration) {
    this.borrowerMongoConfiguration = borrowerMongoConfiguration;
  }

  @Bean
  public MongoCollectorCreditRepository mongoCollectorCreditRepository() {
    return new MongoCollectorCreditRepository(
        borrowerMongoConfiguration
            .database()
            .getCollection("collectorCredit", CollectorCredit.class));
  }
}
