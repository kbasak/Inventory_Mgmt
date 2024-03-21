package com.service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.inventory.entity.Role;
import com.service.inventory.repo.RoleRepo;

@Service
public class RoleService {
	@Autowired
    private RoleRepo rolerepo;

    public Role createNewRole(Role role) {
        return rolerepo.save(role);
    }
}
