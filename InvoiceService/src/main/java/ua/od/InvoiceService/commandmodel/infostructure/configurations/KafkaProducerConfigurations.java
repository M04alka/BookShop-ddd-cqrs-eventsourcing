package ua.od.InvoiceService.commandmodel.infostructure.configurations;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.TotalPriceDto;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfigurations {

    //Producer for TotalPriceDto

    @Bean
    public ProducerFactory<String, TotalPriceDto> producerTotalPriceDto(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, TotalPriceDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerTotalPriceDto());
    }
}
