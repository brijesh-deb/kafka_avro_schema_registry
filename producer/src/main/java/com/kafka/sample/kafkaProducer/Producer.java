package com.kafka.sample.kafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import example.avro.User;

@Service
public class Producer 
{
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "brijesh";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;          // Use this for Avro

    public void sendAvroMessage7()
    {
        logger.info(String.format("Producing message -> Send Avro Message"));
        final String key = "Brijesh";
        User user = new User("Mickey Mouse","Yellow");
        this.kafkaTemplate.send(TOPIC,key, user);
    }

}