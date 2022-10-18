package kafka.practice.notificationmicroservice.configuration.kafka;

import kafka.practice.api.entity.CreditPayedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaCreditPayedEventConsumerConfig {

  private ReceiverOptions<String, CreditPayedEvent> receiverOptions;

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value(value = "${group.id}")
  private String groupId;

  @Value(value = "${topic.payed}")
  private String topic;

  @Bean
  public KafkaReceiver<String, CreditPayedEvent> kafkaPayedEventTemplate() {

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    receiverOptions = ReceiverOptions.create(props);
    var deserializer = new JsonDeserializer<>(CreditPayedEvent.class);
    deserializer.addTrustedPackages("kafka.practice.*");
    receiverOptions = receiverOptions.withValueDeserializer(deserializer);
    receiverOptions = receiverOptions.subscription(Collections.singleton(topic));
    return KafkaReceiver.create(receiverOptions);
  }
}
