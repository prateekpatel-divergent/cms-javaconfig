package com.divergentsl.cmsjavaconfig.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.divergentsl.cmsjavaconfig.dto.DoctorDto;

public class RowMapperDoctor implements RowMapper<DoctorDto>{

	@Override
	public DoctorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		DoctorDto doctorDto = new DoctorDto();
		rs.getString(1);
		rs.getString(2);
		rs.getString(3);
		rs.getString(4);
		rs.getString(5);
		rs.getString(6);
		return null;
	}
	
}
