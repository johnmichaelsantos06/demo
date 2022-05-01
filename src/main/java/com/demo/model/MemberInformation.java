package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "MEMBER_INFO")
public class MemberInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Full name is required")
	@NotBlank(message = "Full name is required")
	@Column(name = "FULL_NAME")
	private String fullName;

	@NotNull(message = "Email address is required")
	@NotBlank(message = "Email address required")
	@Email(message = "Invalid Email address")
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER")
	@NotNull(message = "Gender is required")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "IP_ADDRESS")
	@NotNull(message = "IP address is required")
	@NotBlank(message = "IP address is required")
	@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "Invalid IP address")
	private String ipAddress;

	enum Gender {
		MALE, FEMALE
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
