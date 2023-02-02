//package com.tweetapp.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Configuration
//@AllArgsConstructor
//public class KafkaProducerConfig {
//
//	KafkaProperties kafkaProperties;
//	
//	@Bean
//	public Map<String,Object> producerConfiguration(){
//		log.info("START - producerConfiguration() of KafkaProducerConfig");
//		Map<String, Object> properties = new HashMap<>(kafkaProperties.buildProducerProperties());
//		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		log.info("END - producerConfiguration() of KafkaProducerConfig");
//		return properties;
//	}
//	
//	@Bean
//	ProducerFactory<String, Object> producerFactory(){
//		log.info("START - producerFactory() of KafkaProducerConfig");
//		log.info("END - producerFactory() of KafkaProducerConfig");
//		return new DefaultKafkaProducerFactory<>(producerConfiguration());
//	}
//	
//	@Bean
//	KafkaTemplate<String, Object> kafkaTemplate(){
//		log.info("START - kafkaTemplate() of KafkaProducerConfig");
//		log.info("END - kafkaTemplate() of KafkaProducerConfig");
//		return new KafkaTemplate<>(producerFactory());
//	}
//	
//}
