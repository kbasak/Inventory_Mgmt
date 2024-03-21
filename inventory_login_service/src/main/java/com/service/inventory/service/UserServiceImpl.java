package com.service.inventory.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.inventory.entity.Role;
import com.service.inventory.entity.Users;
import com.service.inventory.repo.RoleRepo;
import com.service.inventory.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void initRoleAndUser() {

		Role adminRole = new Role();

		adminRole.setId(1l);
		adminRole.setName("ROLE_ADMIN");
		
		roleRepo.save(adminRole);

		Role userRole = new Role();

		userRole.setId(2l);
		userRole.setName("ROLE_USER");

		roleRepo.save(userRole);

		Users adminUser = new Users();

		adminUser.setId(1);
		adminUser.setUsername("admin123");
		adminUser.setPassword(getEncodedPassword("admin@pass"));
		adminUser.setName("admin");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setPhone("9876543210");
		adminUser.setDob("18-09-2000");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userRepo.save(adminUser);

	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found for email: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles());
	}

	@Override
	public Users saveUsers(Users users) {
		Users value = userRepo.save(users);
		return value;
	}

	@Override
	public boolean existByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

}
