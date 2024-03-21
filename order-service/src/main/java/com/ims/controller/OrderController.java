package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.kafka.OrderProducer;
import com.ims.model.Order;
import com.ims.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderProducer orderProducer;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderSatus("Pending");
		
		orderService.saveOrder(order);
		
		orderProducer.sendMessage(order);
		return "Order Placed  Successfully";
	}
	
	@PatchMapping("/status/{id}")
	public String changeStatus(@PathVariable long id) {
		//get Order by id
		Order order = orderService.getByOrderId(id).orElse(null);
		
		//change the status to active
		if(order != null) {
			order.setOrderSatus("Accepted");
			orderService.saveOrder(order);
			orderProducer.sendMessage(order);
			return "Order status changed successfully";
		}
		
		
		return "Order Not found";
		
	}
}
