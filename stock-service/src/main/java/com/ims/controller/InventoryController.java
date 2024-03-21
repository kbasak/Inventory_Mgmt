package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.model.Inventory;
import com.ims.service.InventoryService;



@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@GetMapping("/getAll")
	public List<Inventory> getAllProducts() {
		return inventoryService.getAllInventory();
	}
	
	@PostMapping("/save")
	public Inventory saveProduct(@RequestBody Inventory product) {
		return inventoryService.saveProduct(product);
	}

}
