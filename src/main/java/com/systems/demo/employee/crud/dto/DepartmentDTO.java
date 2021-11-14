package com.systems.demo.employee.crud.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.systems.demo.employee.crud.entity.Department;
import com.systems.demo.employee.crud.entity.Employee;

@Component
public class DepartmentDTO {
  
	private Long departmentId;
	private String departmentName;
	private Long managerId;
    private List<Employee> employees;
    
    
    
	public DepartmentDTO() {
		super();
	}
	
	public DepartmentDTO(Long departmentId, String departmentName, Long managerId, List<Employee> employees) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.employees = employees;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Department getDepartmentEntityBean() {
		Department dept = new Department();
		dept.setDepartmentName(departmentName);
		dept.setManagerId(managerId);
		return dept;
	}
    
    
}
