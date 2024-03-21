package com.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

}
