package com.ims.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ims.model.Inventory;
import com.ims.model.Order;
import com.ims.service.InventoryService;



@Service
public class OrderConsumer {
	
	@Autowired
	InventoryService inventoryService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Order order) {
		LOGGER.info("Order event recieved in stock service => ");
		LOGGER.info("Status => " + order.getOrderSatus());
		
		if(order.getOrderSatus().equals("Accepted")) {
			Inventory product = inventoryService.getInventoryById(order.getMaterialId()).orElse(null);
			int newQty = product.getAvailableQty() - order.getOrderQty();
			product.setAvailableQty(newQty);
			inventoryService.saveProduct(product);
		}
		
		
	}

}
