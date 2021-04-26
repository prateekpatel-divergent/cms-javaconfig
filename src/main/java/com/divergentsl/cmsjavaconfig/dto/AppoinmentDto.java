package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AppoinmentDto {

	@NotNull(message = "Patient Name cannot be null")
	@Size(min = 5, max = 20, message = "Name must be between 10 and 20 characters")
	private String PNAME;
	
	@NotNull(message = "Doctor Name cannot be null")
	@Size(min = 5, max = 20, message = "Doctor Name must be between 10 and 20 characters")
	private String DNAME;
	
	@Size(max=100, message = "Problem must be less then 100")
	private String PROBLEM;
	
	private String APPOINMENTDATE;
	
	@DateTimeFormat
	private String CURRENTDATE;
	
	@NotNull(message = "id not be null and example - D001")
	private String DID;
	
	@NotNull(message = "id not be null and example - P001")
	private String PID;
	
	private String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPNAME() {
		return PNAME;
	}
	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}
	public String getDNAME() {
		return DNAME;
	}
	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}
	public String getPROBLEM() {
		return PROBLEM;
	}
	public void setPROBLEM(String pROBLEM) {
		PROBLEM = pROBLEM;
	}
	public String getAPPOINMENTDATE() {
		return APPOINMENTDATE;
	}
	public void setAPPOINMENTDATE(String aPPOINMENTDATE) {
		APPOINMENTDATE = aPPOINMENTDATE;
	}
	public String getCURRENTDATE() {
		return CURRENTDATE;
	}
	public void setCURRENTDATE(String cURRENTDATE) {
		CURRENTDATE = cURRENTDATE;
	}
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	
}
