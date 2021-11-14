package com.systems.demo.employee.crud.dto;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.systems.demo.employee.crud.entity.Department;
import com.systems.demo.employee.crud.entity.Employee;

//@Data
@Component
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class EmployeeDTO {

	private Long employeeId;	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	private Long salary;
	private Long managerId;
	private Department department;
    private Boolean validRequest;
    private String responseMessage;
	
	public Employee getEmployeeEntityBean() {
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setHireDate(hireDate);
		emp.setEmail(email);
		emp.setPhoneNumber(phoneNumber);
		emp.setSalary(salary);
		emp.setManagerId(managerId);
		
		Department dept = new Department();
		dept.setDepartmentId(department.getDepartmentId());
		emp.setDepartment(department);
		return emp;		
	}

	public boolean isEmployeeRequestValid() {
		validRequest = validateFirstName() && validateLastName() && validateSalary()
				&& validatePhoneNumber();
		return validRequest;
		
	}
	public boolean validateFirstName() {
		boolean validFirstName = true; 
		if(firstName == null || firstName.equals("")) {
			validFirstName= false;
			responseMessage = "First Name cannot be null";
		}
		else if(firstName.length() < 2 || firstName.length() > 20) {
			validFirstName= false;
			responseMessage = "First Name Should be greater than 2 and less than or equal to 20 in length";
		}
		return validFirstName;
	}
	
	public boolean validateLastName() {
		boolean validLastName = true; 
		if(lastName == null || lastName.equals("")) {
			validLastName = false;
			responseMessage = "Last Name cannot be null";
		}
		else if(lastName.length() < 2 || lastName.length() > 25) {
			validLastName = false;
			responseMessage = "Last Name Should be greater than 2 and less than or equal to 25 in length";
		}
		return validLastName;
	}
	
	public boolean validateSalary() {
		boolean validSalary = true; 
		if(salary == null) {
			validSalary = false;
			responseMessage = "Salary Can not be null";
		}
		else if(salary <= 0) {
			validSalary = false;
			responseMessage = "Salary should be greater than 0";
		}
		return validSalary;
	}
	
	public boolean validatePhoneNumber() {
		boolean validPhoneNumber = true; 
		Pattern pattern = Pattern.compile("^[0-9\\s\\-]+$");
		Matcher matcher = pattern.matcher(phoneNumber);
		if(!matcher.matches()) {
			validPhoneNumber = false;
			responseMessage = "Phone Number Should Only contain Number and Dashes";
		}
		
		return validPhoneNumber;
	}
	
	public Boolean getValidRequest() {
		return validRequest;
	}

	public void setValidRequest(Boolean validRequest) {
		this.validRequest = validRequest;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

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

	public EmployeeDTO(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
			LocalDate hireDate, Long salary, Long managerId, Department department) {
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

	public EmployeeDTO() {
		super();
	}
	
	
}
