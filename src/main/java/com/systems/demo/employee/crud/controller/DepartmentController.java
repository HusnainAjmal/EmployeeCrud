package com.systems.demo.employee.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.systems.demo.employee.crud.dto.DepartmentDTO;
import com.systems.demo.employee.crud.entity.Department;
import com.systems.demo.employee.crud.entity.Employee;
import com.systems.demo.employee.crud.service.IDepartmentService;
import com.systems.demo.employee.crud.service.IEmployeeService;

@RestController
public class DepartmentController {

	@Autowired
	private IDepartmentService deptService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@GetMapping(value = "/getDepartments")
	public ResponseEntity<Object> getDepartments() {

       List<Department> deparments = deptService.getDepartments();
       return new ResponseEntity<>(deparments, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/getDepartments/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getDepartmentsPaging(
			@PathVariable("pageNo") int pageNo , @PathVariable("pageSize") int pageSize) {

	  if(pageNo < 0 ) {
		  return new ResponseEntity<>("Page Number Should be positive", HttpStatus.BAD_REQUEST);
	   }
       Page<Department> departmentsWithPaging = deptService.getDepartmentsWithPagination(pageNo, pageSize);
       return new ResponseEntity<>(departmentsWithPaging, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getDepartmentById/{deptID}")
	public ResponseEntity<Object> getDepartmentById(@PathVariable("deptID") Long deptID) {

       Department departmentById = deptService.getDepartmentById(deptID);
       if(departmentById == null) {
    	   return new ResponseEntity<>("Department not found for this id :: " + deptID, HttpStatus.NOT_FOUND);
       }
       
       return new ResponseEntity<>(departmentById, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/saveDepartment", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveDepartment(@Valid @RequestBody DepartmentDTO dept) {
	 
	  Department respDepartment = null;
	  try {
		  respDepartment = deptService.saveDepartment(dept.getDepartmentEntityBean());
	  }catch (Exception e) {
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
       return new ResponseEntity<>(respDepartment, HttpStatus.OK);
	}
	
	@PutMapping("/updateDepartment/{id}")
	public ResponseEntity<Object> updateDepartment(@PathVariable(value = "id") Long deptID,
	  @Valid @RequestBody DepartmentDTO departmentDetails)  {
		try{
			Department department = deptService.getDepartmentById(deptID);
		
		if(department == null) {
	       return new ResponseEntity<>("Department not found for this id :: " + deptID, HttpStatus.NOT_FOUND);
	    }
		
		department.setDepartmentName(departmentDetails.getDepartmentName());
		
		List<Employee> allEmployeesByDepartment = employeeService.getAllEmployeesByDepartment(department.getDepartmentId());
	    for(Employee emp : allEmployeesByDepartment) {
	    	emp.setManagerId(departmentDetails.getManagerId());
	    }
		department.setEmployees(allEmployeesByDepartment);
		department.setManagerId(departmentDetails.getManagerId());
		
	    final Department updatedDepartment = deptService.saveDepartment(department);
	    return ResponseEntity.ok(updatedDepartment);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	    	   
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public ResponseEntity<Object> deleteDepartment(@PathVariable(value = "id") Long deptID)
	  {
		try {
		Department department = deptService.getDepartmentById(deptID);

		if(department == null) {
	       return new ResponseEntity<>("Department not found for this id :: " + deptID, HttpStatus.NOT_FOUND);
	    }
		deptService.deleteDepartment(department);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("Department Deleted Sucessfully :: ID : " + deptID, Boolean.TRUE);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	   }catch (Exception e) {
		   return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
     }
	
}
