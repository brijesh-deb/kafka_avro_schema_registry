package com.kafka.sample.consumer.sampleKafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleKafkaConsumerApplication.class, args);
	}

	@Bean
	public Consumer messageProducer() {
		return new Consumer();
	}
}
