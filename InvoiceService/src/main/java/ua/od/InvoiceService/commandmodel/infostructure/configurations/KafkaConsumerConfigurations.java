package ua.od.InvoiceService.commandmodel.infostructure.configurations;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.UserWalletVerificationDto;
import ua.od.InvoiceService.coreappi.events.UserLogedInEvent;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfigurations {

    //Consumer for UserLogedInEvent

    @Bean
    public ConsumerFactory<String,  UserLogedInEvent> consumerUserLogedInEvent(){
        JsonDeserializer< UserLogedInEvent> deserializer = new JsonDeserializer<>( UserLogedInEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"Invoice");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer() ,deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,  UserLogedInEvent > kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, UserLogedInEvent > factory = new ConcurrentKafkaListenerContainerFactory<String,  UserLogedInEvent >();
        factory.setConsumerFactory(consumerUserLogedInEvent());
        return factory;
    }

    //Consumer for UserWalletVerificationDto

    @Bean
    public ConsumerFactory<String, UserWalletVerificationDto> consumerUserWalletVerificationDto(){
        JsonDeserializer<  UserWalletVerificationDto> deserializer = new JsonDeserializer<>(  UserWalletVerificationDto.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"BillCheck");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer() ,deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,  UserWalletVerificationDto > kafkaListenerContainerUserWalletVerificationDtoFactory(){
        ConcurrentKafkaListenerContainerFactory<String,  UserWalletVerificationDto > factory = new ConcurrentKafkaListenerContainerFactory<String,  UserWalletVerificationDto> ();
        factory.setConsumerFactory( consumerUserWalletVerificationDto());
        return factory;
    }
}
