package com.systems.demo.employee.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systems.demo.employee.crud.entity.Department;
import com.systems.demo.employee.crud.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department getDepartmentById(Long deptid) {
		// TODO Auto-generated method stub
		return departmentRepository.findByDepartmentId(deptid);
	}

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Page<Department> getDepartmentsWithPagination(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return departmentRepository.findAll(pageable);
	}

	@Override
	public void deleteDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentRepository.delete(department);
	}

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}


}
