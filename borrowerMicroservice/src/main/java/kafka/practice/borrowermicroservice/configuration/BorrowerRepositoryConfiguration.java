package kafka.practice.borrowermicroservice.configuration;

import kafka.practice.api.entity.Borrower;
import kafka.practice.borrowermicroservice.repository.BorrowerRepository;
import kafka.practice.borrowermicroservice.repository.MongoBorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BorrowerRepositoryConfiguration {

  private BorrowerMongoConfiguration borrowerMongoConfiguration;

  @Autowired
  public BorrowerRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration) {
    this.borrowerMongoConfiguration = borrowerMongoConfiguration;
  }

  @Bean
  public BorrowerRepository borrowerRepository() {
    return new MongoBorrowerRepository(
        borrowerMongoConfiguration.database().getCollection("borrower", Borrower.class));
  }
}
