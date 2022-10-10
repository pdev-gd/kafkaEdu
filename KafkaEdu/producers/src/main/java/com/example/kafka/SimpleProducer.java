package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {

        String topicName = "test12345";

        //Properties
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //KafkaProducer, ProducerRecord
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "k01", "v01");

        //Send ProducerRecord
        kafkaProducer.send(producerRecord);
        kafkaProducer.flush();
        kafkaProducer.close();

    }
}
