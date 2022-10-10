package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {
    public static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class.getName());
    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group-00004");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        kafkaConsumer.subscribe(Arrays.asList("topic1010"));

        while(true){
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record : consumerRecords){
                logger.info("record key:{}, record value:{}, partition:{}, offset:{}",
                        record.key(), record.value(), record.partition(), record.offset());
            }
        }
    }
}

