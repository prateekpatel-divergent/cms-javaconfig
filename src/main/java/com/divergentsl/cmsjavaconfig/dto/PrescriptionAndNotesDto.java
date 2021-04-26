package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PrescriptionAndNotesDto {
	
	@NotNull(message = "id not be null")
	private String PID;
	
	@NotNull(message = "PRESCRIPTION cannot be null")
	@Size(max = 100, message = "PRESCRIPTION must be upto 100 characters")
	private String PRESCRIPTION;
	
	@NotNull(message = "note cannot be null")
	@Size(max = 100, message = "note must be upto 100 characters")
	private String NOTE;
	
	@NotNull(message = "id not be null")
	private String DID;

	@NotNull(message = "id not be null")
	private String PREID;
	
	public String getPREID() {
		return PREID;
	}
	public void setPREID(String pREID) {
		PREID = pREID;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getPRESCRIPTION() {
		return PRESCRIPTION;
	}
	public void setPRESCRIPTION(String pRESCRIPTION) {
		PRESCRIPTION = pRESCRIPTION;
	}
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	

}
