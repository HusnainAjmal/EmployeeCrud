package com.systems.demo.employee.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systems.demo.employee.crud.entity.Employee;
import com.systems.demo.employee.crud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		// TODO Auto-generated method stub
		return employeeRepository.findByEmployeeId(empId);
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
	   employeeRepository.delete(employee);
	}

	@Override
	public Page<Employee> getAllEmployeesWithPaging(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return employeeRepository.findAll(pageable);		
	}
/*
	@Override
	public Page<Employee> getAllEmployeesByDepartmentWithPaging(Long deptID, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return employeeRepository.findEmployeeByDepartmentId(deptID,pageable);
	}
*/
	@Override
	public List<Employee> getAllEmployeesByDepartment(Long deptID) {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeesByDepartmentID(deptID);
	}

}
