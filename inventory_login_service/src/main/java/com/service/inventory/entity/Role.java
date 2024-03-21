package com.service.inventory.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Users> users;

	@Override
	public String getAuthority() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public Role(Long id, String name, Set<Users> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	

	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}