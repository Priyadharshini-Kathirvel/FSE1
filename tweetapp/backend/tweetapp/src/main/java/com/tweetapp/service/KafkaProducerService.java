//package com.tweetapp.service;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import com.tweetapp.model.KafkaMessage;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class KafkaProducerService {
//	
//	@Value("${kafka.topic.name}")
//	private String topic;
//	
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
//	
//	public void sendMessage(KafkaMessage message) {
//		log.info("START - sendMessage() of KafkaProducerService");
//		kafkaTemplate.send(topic,UUID.randomUUID().toString(),message);
//		log.info("END - sendMessage() of KafkaProducerService");
//		
//	}
//	
//}
