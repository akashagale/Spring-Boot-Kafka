package com.consumer.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka // optional in latest version of spring boot
public class LibraryEventConsumerConfig {

    public DefaultErrorHandler errorHandler(){

        // retry the fail record twice one second in between
        FixedBackOff errorHandler = new FixedBackOff(1000L, 2);
        return new DefaultErrorHandler(errorHandler);

    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory
            (ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
                            ObjectProvider<ConsumerFactory<Object, Object>> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory
                = new ConcurrentKafkaListenerContainerFactory();
        configurer.configure(factory, (ConsumerFactory)kafkaConsumerFactory.getIfAvailable());
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        factory.setConcurrency(3); // this option is applicable for non cloud environment
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }
}
