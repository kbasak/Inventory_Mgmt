package com.service.inventory.dto;

public class UserDetailsDTO {
	private long id;
	private String username;
	private String name;
	private String email;
	private String phone;
	private String dob;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public UserDetailsDTO(long id, String username, String name, String email, String phone, String dob) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
	}

}
