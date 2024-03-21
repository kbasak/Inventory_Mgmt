package com.service.inventory.controller;

import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.inventory.dto.RegistrationResponse;
import com.service.inventory.dto.UserDTO;
import com.service.inventory.entity.Role;
import com.service.inventory.entity.Users;
import com.service.inventory.exception.UserAlreadyExistException;
import com.service.inventory.service.UserService;

@RestController
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;
	
	@PostConstruct
    public void initRoleAndUser() {
		userService.initRoleAndUser();
    }

	@PostMapping(path = "/register")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid UserDTO userDTO) throws UserAlreadyExistException {
		boolean b = this.userService.existByUsername(userDTO.getUsername());
		if (b) {
			throw new UserAlreadyExistException("User Already Exist: " + userDTO.getUsername());
		} else {
			Users users = new Users();
			users.setUsername(userDTO.getUsername());
			users.setPassword(encoder.encode(userDTO.getPassword()));
			users.setName(userDTO.getName());
			users.setEmail(userDTO.getEmail());
			users.setPhone(userDTO.getPhone());
			users.setDob(userDTO.getDob());

			HashSet<Role> roles = new HashSet<>();
			Role role = new Role();
			role.setId(2l);
			roles.add(role);
			users.setRoles(roles);

			this.userService.saveUsers(users);
			return new ResponseEntity<Object>(
					new RegistrationResponse(users.getId(), users.getUsername(), users.getPassword(), users.getName()),
					HttpStatus.CREATED);
		}
	}
}
