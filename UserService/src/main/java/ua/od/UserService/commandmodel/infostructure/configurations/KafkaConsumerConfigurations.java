package ua.od.UserService.commandmodel.infostructure.configurations;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ua.od.UserService.commandmodel.infostructure.helpers.TotalPriceDto;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfigurations {

    @Bean
    public ConsumerFactory<String, TotalPriceDto> consumerFactory(){
        JsonDeserializer< TotalPriceDto > deserializer = new JsonDeserializer<>( TotalPriceDto.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"InvoiceBill");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer() ,deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,  TotalPriceDto  > kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, TotalPriceDto  > factory = new ConcurrentKafkaListenerContainerFactory<String,  TotalPriceDto >();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
