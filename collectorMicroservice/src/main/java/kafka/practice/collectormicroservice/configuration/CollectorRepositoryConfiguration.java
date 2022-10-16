package kafka.practice.collectormicroservice.configuration;

import kafka.practice.api.entity.Collector;
import kafka.practice.api.entity.CollectorCredit;
import kafka.practice.collectormicroservice.repository.MongoCollectorCreditRepository;
import kafka.practice.collectormicroservice.repository.MongoCollectorRepository;
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
  public MongoCollectorRepository mongoCollectorRepository() {
    return new MongoCollectorRepository(
        borrowerMongoConfiguration.database().getCollection("collector", Collector.class));
  }
}
