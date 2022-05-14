package com.demo.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, gender, id, ipAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberInformationDTO other = (MemberInformationDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && gender == other.gender
				&& Objects.equals(id, other.id) && Objects.equals(ipAddress, other.ipAddress);
	}

}
