package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PatientDto {
	
	@NotNull(message = "id not be null and example - P001")
	private String ID;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 5, max = 20, message = "Name must be between 10 and 20 characters")
	private String PNAME;
	
	@NotNull(message = "Address cannot be null")
	@Size(max = 40, message = "Address must be upto 40 characters")
	private String ADDRESS;
	
	@Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 80, message = "Age should not be greater than 80")
	private String AGE;
	
	@Min(value = 0, message = "Weight should not be less than 0")
    @Max(value = 150, message = "Weight should not be greater than 150")
	private String WEIGHT;
	
	@Size(max= 10, message = "Gender must less than 10")
	private String GENDER;
	
	@Size(max =10, message = "Mobile number should be 10")
	private String CONTACTNO;
	
	@DateTimeFormat
	private String ACURRENTDATE;
	
	private String APPOINTMENTDATE;
	
	@Size(max=100, message = "Problem must be less then 100")
	private String PROBLEM;
	
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
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getWEIGHT() {
		return WEIGHT;
	}
	public void setWEIGHT(String wEIGHT) {
		WEIGHT = wEIGHT;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getCONTACTNO() {
		return CONTACTNO;
	}
	public void setCONTACTNO(String cONTACTNO) {
		CONTACTNO = cONTACTNO;
	}
	public String getACURRENTDATE() {
		return ACURRENTDATE;
	}
	public void setACURRENTDATE(String aCURRENTDATE) {
		ACURRENTDATE = aCURRENTDATE;
	}
	public String getAPPOINTMENTDATE() {
		return APPOINTMENTDATE;
	}
	public void setAPPOINTMENTDATE(String aPPOINTMENTDATE) {
		APPOINTMENTDATE = aPPOINTMENTDATE;
	}
	public String getPROBLEM() {
		return PROBLEM;
	}
	public void setPROBLEM(String pROBLEM) {
		PROBLEM = pROBLEM;
	}
	
}
