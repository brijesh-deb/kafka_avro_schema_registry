package com.kafka.sample.consumer.sampleKafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import example.avro.User;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "brijesh", groupId = "group_id")
    public void consume(ConsumerRecord<String, User> record) {
        LOGGER.info(String.format("Consumed message -> %s", record.value()));
    }
}