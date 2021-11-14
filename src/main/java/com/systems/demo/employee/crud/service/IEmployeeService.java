package com.systems.demo.employee.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.systems.demo.employee.crud.entity.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long empId);

	Employee saveEmployee(Employee emp);

	void deleteEmployee(Employee employee);

	Page<Employee> getAllEmployeesWithPaging(int pageNo, int pageSize);

	//Page<Employee> getAllEmployeesByDepartmentWithPaging(Long deptID, int pageNo, int pageSize);

	List<Employee> getAllEmployeesByDepartment(Long deptID);

}
