package com.systems.demo.employee.crud.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Getter
//@Setter
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
  
	@Id
	@Column(name = "employee_id", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id_generator")
	@SequenceGenerator(name="emp_id_generator", sequenceName = "emp_id_seq", allocationSize=1)
	private Long employeeId;
	
	@Column(name = "first_name", nullable = false , length = 20)
    @Size(min = 2, max = 20 ,
    message = "First Name Should be greater than 2 and less than or equal to 20 in length")
	@NotNull(message = "First Name cannot be null")
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 25)
    @Size(min = 2, max = 25 ,
    message = "Last Name should be greater than 2 and less than or equal to 25 in length")
	@NotNull(message = "Last Name cannot be null")
	private String lastName;
	
	@Column(name = "email", length = 25)
	@Email(message = "Email should be valid")
	@Size(max = 25 ,
    message = "Email can only be 25 Characters Long")
	private String email;
	
	@Column(name = "phone_number", length = 20)
    @Size(max = 20 ,
    message = "Phone Number can only be 20 Characters Long")
	private String phoneNumber;
	
	
	@Column(name = "hire_date", columnDefinition = "DATE")
	private LocalDate hireDate;
	
	
	@Column(name = "salary", length = 8)
	@Min(value = 1, message = "Salary should be greater than 0")
	@Positive
	private Long salary;
	
	@Column(name = "manager_id", length = 6)
	private Long managerId;
	
	@ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(
			name= "department_id",
			referencedColumnName = "department_id"
	)
	@JsonIgnore
	private Department department;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(Long employeeId,
			@Size(min = 2, max = 20, message = "First Name Should be greater than 2 and less than or equal to 20 in length") @NotNull(message = "First Name cannot be null") String firstName,
			@Size(min = 2, max = 25, message = "Last Name should be greater than 2 and less than or equal to 25 in length") @NotNull(message = "Last Name cannot be null") String lastName,
			@Email(message = "Email should be valid") @Size(max = 25, message = "Email can only be 25 Characters Long") String email,
			@Size(max = 20, message = "Phone Number can only be 20 Characters Long") String phoneNumber,
			LocalDate hireDate, @Min(value = 1, message = "Salary should be greater than 0") @Positive Long salary,
			Long managerId, Department department) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.salary = salary;
		this.managerId = managerId;
		this.department = department;
	}

	public Employee() {
		super();
	}
	
	
	
}
