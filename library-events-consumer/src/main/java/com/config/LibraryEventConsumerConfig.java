package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka // optional in latest version of spring boot
public class LibraryEventConsumerConfig {
}
