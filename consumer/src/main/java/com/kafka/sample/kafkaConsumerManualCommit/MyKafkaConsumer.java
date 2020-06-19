package com.kafka.sample.kafkaConsumerManualCommit;

import example.avro.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MyKafkaConsumer.class);

    private KafkaConsumer<String, User> kafkaConsumer;

    public MyKafkaConsumer(String topicName, Properties consumerProperties) 
    {
        logger.info("Inside MyKafkaConsumer constructor");
        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(topicName));
        logger.info("Inside MyKafkaConsumer constructor - END");
    }

    public void runSingleWorker() 
    {
        while(true) 
        {
            ConsumerRecords<String, User> records = kafkaConsumer.poll(100);
            
            for (ConsumerRecord<String, User> record : records)
            {
                String message = record.toString();
                logger.info("MyKafkaConsumer Received message \n");
                logger.info("offset = "+record.offset());
                logger.info("Key = "+ record.key());
                logger.info("Value = "+record.value());

                Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();
                commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));
                kafkaConsumer.commitSync(commitMessage);
                logger.info("Offset committed to Kafka.");

            }
        }
    }
}