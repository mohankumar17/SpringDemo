package com.springboot.thymeleafdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	

	@NotBlank(message = "First Name is required")
	@Column(name="first_name")
	private String firstName;

	@NotBlank(message = "Last Name is required")
	@Column(name="last_name")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Please Provide Valid Email")
	@Column(name="email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department theDepartment;
	
		
	// define constructors

	
	public Employee(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// define getter/setter
	
	public int getId() {
		return id;
	}

	public void setTheDepartment(Department theDepartment) {
		//prevent endless loop
		if (sameAsFormer(theDepartment))
			return ;
		this.theDepartment = theDepartment;
	}

	private boolean sameAsFormer(Department newDepartment) {
		return Objects.equals(theDepartment, newDepartment);
	}

		
}











