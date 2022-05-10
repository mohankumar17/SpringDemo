package com.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="employee")
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
	
	public Employee() {
		
	}
	
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

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getTheDepartment() {
		return theDepartment;
	}

	/*public void setTheDepartment(Department theDepartment) {
		this.theDepartment = theDepartment;
	}*/

	public void setTheDepartment(Department theDepartment) {
		//prevent endless loop
		if (sameAsFormer(theDepartment))
			return ;
		this.theDepartment = theDepartment;
	}

	private boolean sameAsFormer(Department newDepartment) {
		return Objects.equals(theDepartment, newDepartment);
	}

	// define tostring

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
		
}











