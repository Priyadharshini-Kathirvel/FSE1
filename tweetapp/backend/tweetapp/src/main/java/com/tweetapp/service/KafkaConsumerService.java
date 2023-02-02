//package com.tweetapp.service;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//import com.tweetapp.model.KafkaMessage;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class KafkaConsumerService {
//
//	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}", containerFactory = "kafkaListenerContainerFactory")
//	public void listen(@Payload KafkaMessage kafkaMessage) {
//		log.info("START - listen() of KafkaConsumerService");
//		log.info("Message received. Message ID : {}, Message : {}, Date : {}", kafkaMessage.getId(),
//				kafkaMessage.getMessage(), kafkaMessage.getMessageDate());
//		log.info("END - listen() of KafkaConsumerService");
//	}
//
//}
