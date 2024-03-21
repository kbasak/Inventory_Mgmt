package com.service.inventory.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.service.inventory.dto.UserDetailsDTO;
import com.service.inventory.dto.UsersDeatilsDTO;
import com.service.inventory.entity.Role;
import com.service.inventory.entity.Users;
import com.service.inventory.repo.UserRepo;
import com.service.inventory.util.JwtUtil;

@RestController
public class ServiceController {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserRepo userRepo;

	@GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDTO> getUserDetails(@RequestHeader(name = "Authorization") String tokenDup) {

		String token = tokenDup.substring(7);

		String username = jwtTokenUtil.extractUsername(token);

		Users users = userRepo.findByUsername(username);

		return new ResponseEntity<>(new UserDetailsDTO(users.getId(), users.getUsername(), users.getName(),
				users.getEmail(), users.getPhone(), users.getDob()), HttpStatus.OK);

	}

//	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
//	public String getAllUserDetails(@RequestHeader(name = "Authorization") String tokenDup) {
//
//		String token = tokenDup.substring(7);
//
//		List<Users> users = userRepo.findAll();
//		 
//		System.out.println("Users       "+users);
//		System.out.println("Users       "+users.toString());
//		return users.toString();
//
//	}

	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Users>> getAllUserDetails(@RequestHeader(name = "Authorization") String tokenDup) {

		List<Users> result = userRepo.findAll();
		Iterator<Users> it = result.iterator();
		List<Users> usersList = new ArrayList<>();

		while (it.hasNext()) {
			Users row = it.next();
			if (row.getRoles().toString().equals("[ROLE_USER]")) {
				Users users = new Users();
				users.setId(row.getId());
				users.setUsername(row.getUsername());
				users.setPassword(row.getPassword());
				users.setName(row.getName());
				users.setEmail(row.getEmail());
				users.setPhone(row.getPhone());
				users.setDob(row.getDob());

				usersList.add(users);
			}

		}
		return new ResponseEntity<List<Users>>(usersList, HttpStatus.OK);

	}

	@GetMapping(path = "/checkUser/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDeatilsDTO> getUsersDetails(@RequestHeader(name = "Authorization") String tokenDup,
			@PathVariable String username) {

		Users users = userRepo.findByUsername(username);

		return new ResponseEntity<>(
				new UsersDeatilsDTO(users.getId(), users.getName(), users.getEmail(), users.getPhone(), users.getDob()),
				HttpStatus.OK);

	}

	@PutMapping(path = "/upgradeUser/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> upgradeUser(@RequestHeader(name = "Authorization") String tokenDup,
			@PathVariable String username) {

		Users users = userRepo.findByUsername(username);

		Users user = new Users();

		user.setId(users.getId());
		user.setUsername(users.getUsername());
		user.setPassword(users.getPassword());
		user.setName(users.getName());
		user.setEmail(users.getEmail());
		user.setPhone(users.getPhone());
		user.setDob(users.getDob());

		HashSet<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1l);
		roles.add(role);
		user.setRoles(roles);

		userRepo.save(user);

		return new ResponseEntity<>(new String("User upgraded successfully"), HttpStatus.OK);

	}
	
	@PutMapping(path = "/updateUserProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUserDetails(@RequestHeader(name="Authorization") String tokenDup, @PathVariable String username){
		
		Users users = userRepo.findByUsername(username);
		this.userRepo.save(users);
		
		return null;
	}
	
}













