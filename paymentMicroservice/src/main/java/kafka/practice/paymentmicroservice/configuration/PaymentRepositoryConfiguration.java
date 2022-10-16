package kafka.practice.paymentmicroservice.configuration;

import kafka.practice.api.entity.Payment;
import kafka.practice.paymentmicroservice.repository.MongoPaymentRepository;
import kafka.practice.paymentmicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRepositoryConfiguration {

  private BorrowerMongoConfiguration borrowerMongoConfiguration;

  @Autowired
  public PaymentRepositoryConfiguration(BorrowerMongoConfiguration borrowerMongoConfiguration) {
    this.borrowerMongoConfiguration = borrowerMongoConfiguration;
  }

  @Bean
  public PaymentRepository paymentRepository() {
    return new MongoPaymentRepository(
        borrowerMongoConfiguration.database().getCollection("payment", Payment.class));
  }
}
