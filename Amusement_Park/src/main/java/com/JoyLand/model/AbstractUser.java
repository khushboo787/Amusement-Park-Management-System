package com.JoyLand.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class AbstractUser {
	
	@NotBlank(message = "Please provide user's name")
	@Size(min = 3,  message = "Username should be minimum 3  characters")
	private String username;
	
	@NotBlank(message = "Please provide password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotBlank
	private String address;
	
	@Size(min = 10, message = "Mobile Number should be of 10 digits")
	private String mobileNumber;
	
	@NotNull(message = "Please provide email id")
	@Pattern(regexp = "[a-z0-9.]+@[a-z0-9.]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE, message="Invalid email id")
	@Column(unique = true, nullable = false)
	private String email;
}
