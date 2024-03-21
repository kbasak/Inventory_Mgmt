package com.service.inventory.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatingDTO {
	@Id
    @JsonProperty
    private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public ValidatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public ValidatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
