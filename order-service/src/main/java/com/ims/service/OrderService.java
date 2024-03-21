package com.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Order;
import com.ims.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo repo;
	
	public List<Order> getAllOrders() {
		return repo.findAll();
	}
	public Optional<Order> getByOrderId(long id) {
		return repo.findById(id);
	}
	public Order saveOrder(Order order) {
		return repo.save(order);
	}
	

}
