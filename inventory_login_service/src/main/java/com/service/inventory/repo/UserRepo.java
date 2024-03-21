package com.service.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.inventory.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

	boolean existsByUsername(String username);

	Users findByUsername(String username);

}
