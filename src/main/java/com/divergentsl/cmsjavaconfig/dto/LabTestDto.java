package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class LabTestDto {
	
	@NotNull(message = "id not be null and example - LT001")
	private String PID;
	
	@NotNull(message = "Test Name cannot be null")
	@Size(max = 50, message = "Test Name must be 50 characters")
	private String TEST;
	
	@DateTimeFormat
	private String TCURRENTDATE;
	
	@Size(message = "rate not be null")
	private String RATE;

	private String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getTEST() {
		return TEST;
	}
	public void setTEST(String tEST) {
		TEST = tEST;
	}
	public String getTCURRENTDATE() {
		return TCURRENTDATE;
	}
	public void setTCURRENTDATE(String tCURRENTDATE) {
		TCURRENTDATE = tCURRENTDATE;
	}
	public String getRATE() {
		return RATE;
	}
	public void setRATE(String rATE) {
		RATE = rATE;
	}

}
