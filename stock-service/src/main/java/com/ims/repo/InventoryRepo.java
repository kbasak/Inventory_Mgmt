package com.ims.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.model.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long>{
	
	Optional<Inventory> findInventoryByLocationNumber(String locationNumber);
	
	Optional<Inventory> findInventoryByMaterialName(String materialName);

}
