package com.jsp.agro.entity;

import jakarta.validation.constraints.*;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.jsp.agro.enums.UserType;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name is mandatory")
	private String firstName;

	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name is mandatory")
	private String lastName;

	@Column(unique = true)
	@Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email id")
	private String email;

	@Min(value = 6000000000l, message = " phone number must be valid")
	@Max(value = 9999999999l, message = " phone number must be valid")
	private long phone;

	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "must contain at least one uppercase letter, one lowercase letter,one number, one special character")
	private String password;

	private String gender;
	private int age;

	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Enumerated(EnumType.STRING)
	private UserType type;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;

}
