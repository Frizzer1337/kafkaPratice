package kafka.practice.collectormicroservice.configuration;

import kafka.practice.api.entity.Credit;
import kafka.practice.collectormicroservice.repository.CreditRepository;
import kafka.practice.collectormicroservice.repository.MongoCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditRepositoryConfiguration {

  private BorrowerMongoConfiguration borrowerMongoConfiguration;

  @Autowired
  public CreditRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration) {
    this.borrowerMongoConfiguration = borrowerMongoConfiguration;
  }

  @Bean
  public CreditRepository creditRepository() {
    return new MongoCreditRepository(
        borrowerMongoConfiguration.database().getCollection("credit", Credit.class));
  }
}
