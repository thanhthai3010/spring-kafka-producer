package com.producer.config;


import com.producer.model.UserModel;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ProducerFactory<String, UserModel> userFactoryProducer() {
        Map<String, Object> config = new HashMap<>();

        config.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer );
        config.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
        config.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class );

        return new DefaultKafkaProducerFactory<>( config );
    }

    @Bean
    public KafkaTemplate<String, UserModel> kafkaTemplate() {
        return new KafkaTemplate<>( userFactoryProducer() );
    }
}
