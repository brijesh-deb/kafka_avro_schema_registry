package com.kafka.sample.kafkaConsumerManualCommit;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import example.avro.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@SpringBootApplication
public class KafkaConsumerManualCommitApplication implements CommandLineRunner
{
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerManualCommitApplication.class);
    private static final String TOPIC = "patient-data";

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerManualCommitApplication.class, args);
    }

	 @Override
	 public void run(String... args)
	 {
		 	logger.info("Inside consumer application run");
	        Properties consumerProperties = new Properties();
	        consumerProperties.put("bootstrap.servers", "localhost:9092");
	        consumerProperties.put("zookeeper.session.timeout.ms", "6000");
	        consumerProperties.put("zookeeper.sync.time.ms","2000");
	        consumerProperties.put("auto.commit.enable", "false");
	        consumerProperties.put("auto.commit.interval.ms", "1000");
	        consumerProperties.put("consumer.timeout.ms", "-1");
	        consumerProperties.put("max.poll.records", "1");
	        consumerProperties.put("value.deserializer", "com.kafka.sample.kafkaConsumerManualCommit.AvroDeserializer");
	        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        consumerProperties.put("group.id", "avro");

	        Thread kafkaConsumerThread = new Thread(() -> {
	            logger.info("Starting Kafka consumer thread.");
	            MyKafkaConsumer myKafkaConsumer = new MyKafkaConsumer(TOPIC, consumerProperties);
				logger.info("1111 Before runSingleWorker");
	            myKafkaConsumer.runSingleWorker();
	        });

	        kafkaConsumerThread.start();
	 }

//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
////	        consumerProperties.put("zookeeper.session.timeout.ms", "6000");
////	        consumerProperties.put("zookeeper.sync.time.ms","2000");
////	        consumerProperties.put("consumer.timeout.ms", "-1");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"1");
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avro");
//
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, User> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
//                new AvroDeserializer<>(User.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, User> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
}