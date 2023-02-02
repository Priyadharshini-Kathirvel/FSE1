//package com.tweetapp.config;
//
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Configuration
//@AllArgsConstructor
//public class KafkaConsumerConfig {
//
//	KafkaProperties kafkaProperties;
//
//	@Bean
//	ConsumerFactory<String, Object> consumerFactory() {
//		
//		log.info("START - consumerFactory() of KafkaConsumerConfig");
//		final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
//		jsonDeserializer.addTrustedPackages("*");
//		log.info("END - consumerFactory() of KafkaConsumerConfig");
//		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(),
//				jsonDeserializer);
//	}
//	
//	@Bean
//	ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(){
//		log.info("START - kafkaListenerContainerFactory() of KafkaConsumerConfig");
//		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory());
//		log.info("END - kafkaListenerContainerFactory() of KafkaConsumerConfig");
//		return factory;
//	}
//	
//
//}
