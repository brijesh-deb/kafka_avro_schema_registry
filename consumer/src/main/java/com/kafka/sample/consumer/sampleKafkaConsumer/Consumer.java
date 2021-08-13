package com.kafka.sample.consumer.sampleKafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import example.avro.User;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    
    @KafkaListener(topics = "brijesh")
    public void consume(final ConsumerRecord<Long, User> consumerRecord) {
    	LOGGER.info("received {} {}", consumerRecord.key(), consumerRecord.value());

      }
}