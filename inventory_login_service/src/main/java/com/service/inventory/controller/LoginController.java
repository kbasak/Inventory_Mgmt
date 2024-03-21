package com.service.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.service.inventory.dto.AuthenticationResponse;
import com.service.inventory.dto.LoginDTO;
import com.service.inventory.dto.ValidatingDTO;
import com.service.inventory.entity.Users;
import com.service.inventory.exception.UserNotFoundException;
import com.service.inventory.repo.UserRepo;
import com.service.inventory.service.UserService;
import com.service.inventory.service.UserServiceImpl;
import com.service.inventory.util.JwtUtil;

@RestController
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	private ValidatingDTO validatingDTO = new ValidatingDTO();

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody LoginDTO loginDTO)
			throws UserNotFoundException {

		boolean b = this.userService.existByUsername(loginDTO.getUsername());
		if (!b) {
			throw new UserNotFoundException("User Not Found : " + loginDTO.getUsername());
		} else {

			Users users = new Users();
			users.setUsername(loginDTO.getUsername());
			users.setPassword(loginDTO.getPassword());

			final UserDetails userDetails = userServiceImpl.loadUserByUsername(users.getUsername());

			Users r = userRepo.findByUsername(users.getUsername());
			String username = r.getUsername();
			String password = r.getPassword();

			System.out.println(userRepo.findByUsername(username).getRoles().toArray()[0]);

			// if (userDetails.getPassword().equals(users.getPassword())) {
			if (passwordEncoder.matches(users.getPassword(), userDetails.getPassword())) {
				return new ResponseEntity<>(
						new AuthenticationResponse(username, password, jwtTokenUtil.generateToken(userDetails),
								jwtTokenUtil.getCurrentTime(), jwtTokenUtil.getExpirationTime()),
						HttpStatus.OK);
			}

			return new ResponseEntity<>(
					new AuthenticationResponse(username, password, jwtTokenUtil.generateToken(userDetails),
							jwtTokenUtil.getCurrentTime(), jwtTokenUtil.getExpirationTime()),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidatingDTO> validatingAuthorizationToken(
			@RequestHeader(name = "Authorization") String tokenDup) {
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userServiceImpl.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				validatingDTO.setValidStatus(true);
				return new ResponseEntity<>(validatingDTO, HttpStatus.OK);
			} else {
				throw new Exception("Invalid token");
			}
		} catch (Exception e) {
			validatingDTO.setValidStatus(false);
			return new ResponseEntity<>(validatingDTO, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
