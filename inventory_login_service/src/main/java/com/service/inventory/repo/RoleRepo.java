package com.service.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.inventory.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
