package com.ims.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ims.model.Order;
import com.ims.service.EmailService;

@Service
public class OrderConsumer {
	
	@Autowired
	EmailService emailService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
	
	private boolean shouldSendEmail = false;
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Order order) {
		LOGGER.info("Order event received in email service with status => " + order.getOrderSatus());
		
		if(order.getOrderSatus().equals("Pending")) {
			shouldSendEmail = true;
			sendEmailToAdmin();
		}else {
			shouldSendEmail = false;
		}
	}
	
	@Scheduled(fixedDelay = 60000)
	public void sendEmailToAdmin() {
		if(shouldSendEmail) {
			String subject = "Order Approval Reminder";
			String recipient = "aniketjha0056@outlook.com".trim();
			String content = "Please review and approve the pending orders";
			emailService.sendEmail(subject, recipient, content);
			
		}
	}

}
