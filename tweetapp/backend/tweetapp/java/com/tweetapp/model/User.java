package com.tweetapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	@NotBlank(message = "Firstname cannot be empty.")
	private String firstName;
	@NotBlank(message = "Lastname cannot be empty.")
	private String lastName;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Gender cannot be empty.")
	private String gender;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "DOB cannot be empty")
	private String dateOfBirth;
	@Id
	@Email
	@NotBlank(message = "Email cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Invalid Email Address")
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Password cannot be empty.")
	private String password;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Confirm Password cannot be empty.")
	private String confirmPassword;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Mobile number cannot be empty.")
	@Pattern(regexp = "^[6789][0-9]{9}", message = "Invalid Mobile Number")
	private String mobileNumber;
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean isLoggedIn;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Tweet> tweets = new ArrayList<>();

}
