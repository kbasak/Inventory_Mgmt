package com.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Inventory;
import com.ims.repo.InventoryRepo;



@Service
public class InventoryService {
	
	@Autowired
	InventoryRepo repo;
	
	public List<Inventory> getAllInventory() {
		return repo.findAll();
	}
	
	public Optional<Inventory> getInventoryById(Long id) {
		return repo.findById(id);
	}
	
	public Inventory saveProduct(Inventory product) {
		return repo.save(product);
	}
	
	public void deleteProduct(Inventory product) {
		repo.delete(product);
	}
	
	public Optional<Inventory> getInventoryByLocationNumber(String locationNumber) {
		return repo.findInventoryByLocationNumber(locationNumber);
	}
	
	public Optional<Inventory> getInventoryByMaterialName(String materialName) {
		return repo.findInventoryByMaterialName(materialName);
	}

}
