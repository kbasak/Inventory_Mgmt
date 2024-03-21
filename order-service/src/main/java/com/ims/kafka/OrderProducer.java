package com.ims.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ims.model.Order;

@Service
public class OrderProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
	
	private NewTopic topic;
	
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public OrderProducer(NewTopic topic, KafkaTemplate<String, Order> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(Order order) {
		LOGGER.info(String.format("Order event => %s", order.toString()));
		
		//create a message
		Message<Order> message = MessageBuilder
				.withPayload(order)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		
		kafkaTemplate.send(message);
	}
}
