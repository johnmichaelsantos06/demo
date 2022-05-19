package com.demo.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.demo.model.Gender;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberInformationDTO {
	private Integer id;
	
	@NotNull(message = "Full name is required")
	@NotBlank(message = "Full name is required")
	private String fullName;
	
	@NotNull(message = "Email address is required")
	@NotBlank(message = "Email address required")
	@Email(message = "Invalid Email address")
	private String email;
	
	@NotNull(message = "Gender is required")
	private Gender gender;
	
	@NotNull(message = "IP address is required")
	@NotBlank(message = "IP address is required")
	@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "Invalid IP address")
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
