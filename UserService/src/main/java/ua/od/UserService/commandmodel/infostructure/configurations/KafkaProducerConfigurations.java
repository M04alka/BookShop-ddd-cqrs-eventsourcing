package ua.od.UserService.commandmodel.infostructure.configurations;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ua.od.UserService.commandmodel.infostructure.helpers.UserWalletVerificationDto;
import ua.od.UserService.coreappi.events.UserLogedInEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfigurations {

    //Producer for UserLogedInEvent

    @Bean
    public ProducerFactory<String, UserLogedInEvent> producerUserLogedInEvent(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    //Template for UserLogedInEvent

    @Bean
    public KafkaTemplate<String, UserLogedInEvent> userLogedInEventTemplate(){
        return new KafkaTemplate<>(producerUserLogedInEvent());
    }

    //Producer for WalletVerification

    @Bean
    public ProducerFactory<String, UserWalletVerificationDto> producerString(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    //Template for WalletVerification

    @Bean
    public KafkaTemplate<String, UserWalletVerificationDto> userWalletVerificationDtoTemplate(){
        return new KafkaTemplate<>(producerString());
    }
}
