package com.service.inventory.service;

import com.service.inventory.entity.Users;

public interface UserService {
	public Users saveUsers(Users users);

	public boolean existByUsername(String username);
	
	public void initRoleAndUser();
}
