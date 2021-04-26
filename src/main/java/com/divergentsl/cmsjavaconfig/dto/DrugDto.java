package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DrugDto {

	@NotNull(message = "id not be null and example - D0001")
	private String ID;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 5, max = 20, message = "Name must be between 10 and 20 characters")
	private String NAME;
	
	@Size(message = "rate not be null")
	private String RATE;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getRATE() {
		return RATE;
	}
	public void setRATE(String rATE) {
		RATE = rATE;
	}

}
