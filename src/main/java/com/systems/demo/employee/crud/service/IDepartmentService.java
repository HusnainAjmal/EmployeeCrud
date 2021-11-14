package com.systems.demo.employee.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.systems.demo.employee.crud.entity.Department;

public interface IDepartmentService {

	Department saveDepartment(Department department);
	
	List<Department> getDepartments();
	
	Page<Department> getDepartmentsWithPagination(int pageNo, int pageSize);
	
	Department getDepartmentById(Long deptid);
	
	void deleteDepartment(Department department);

}
