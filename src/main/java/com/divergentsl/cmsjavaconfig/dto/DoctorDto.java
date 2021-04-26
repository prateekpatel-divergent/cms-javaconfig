package com.divergentsl.cmsjavaconfig.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DoctorDto {

	@NotNull(message = "id not be null and example - D001")
	private String id;

	@NotNull(message = "Name cannot be null")
	@Size(min = 5, max = 20, message = "Name must be between 10 and 20 characters")
	private String name;

	@NotNull(message = "speciality cannot be null")
	private String speciality;

	@Size(min=10,max =10, message = "Mobile number should be 10")
	private String contactno;

	private String fee;

	@NotNull(message = "degree cannot be null")	
	private String degree;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
