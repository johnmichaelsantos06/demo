package com.demo.mappers;

import com.demo.model.Gender;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberInformationDTO {
	private Integer id;
	private String fullName;
	private String email;
	private Gender gender;
	private String ipAddress;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public MemberInformationDTO(
			@JsonProperty("id") Integer id, 
			@JsonProperty("fullName") String fullName, 
			@JsonProperty("email") String email, 
			@JsonProperty("gender") Gender gender, 
			@JsonProperty("ipAddress") String ipAddress) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.gender = gender;
		this.ipAddress = ipAddress;
	}

	public Integer getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public Gender getGender() {
		return gender;
	}

	public String getIpAddress() {
		return ipAddress;
	}

}
