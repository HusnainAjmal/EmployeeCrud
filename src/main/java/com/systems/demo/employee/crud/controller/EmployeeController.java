package com.systems.demo.employee.crud.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.JDBCException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systems.demo.employee.crud.dto.EmployeeDTO;
import com.systems.demo.employee.crud.entity.Department;
import com.systems.demo.employee.crud.entity.Employee;
import com.systems.demo.employee.crud.service.IDepartmentService;
import com.systems.demo.employee.crud.service.IEmployeeService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDepartmentService deptService;
	
	@GetMapping(value = "/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {

       List<Employee> employees = employeeService.getAllEmployees();
       return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getAllEmployees/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAllEmployeesPaging(
			@PathVariable("pageNo") int pageNo , @PathVariable("pageSize") int pageSize) {

	  if(pageNo < 0 ) {
		  return new ResponseEntity<>("Page Number Should be positive", HttpStatus.BAD_REQUEST);
	   }
       Page<Employee> allEmployeesWithPaging = employeeService.getAllEmployeesWithPaging(pageNo, pageSize);
       return new ResponseEntity<>(allEmployeesWithPaging, HttpStatus.OK);
		
	}
	/*
	@GetMapping(value = "/getEmployeesByDeparmentId/{deptID}/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getEmployeesByDeparmentIdWithPaging(@PathVariable("deptID") Long deptID,
			@PathVariable("pageNo") int pageNo , @PathVariable("pageSize") int pageSize) {

	   if(deptID == null) {
	      return new ResponseEntity<>("Department ID is required", HttpStatus.BAD_REQUEST);
       }
	  if(pageNo < 0 ) {
		  return new ResponseEntity<>("Page Number Should be positive", HttpStatus.BAD_REQUEST);
	   }
	  
       Page<Employee> allEmployeesWithPaging = employeeService.getAllEmployeesByDepartmentWithPaging(deptID, pageNo, pageSize);
       return new ResponseEntity<>(allEmployeesWithPaging, HttpStatus.OK);
		
	}
	*/
	@GetMapping(value = "/getEmployeesByDeparmentId/{deptID}")
	public ResponseEntity<Object> getEmployeesByDeparmentId(@PathVariable("deptID") Long deptID) {

	   if(deptID == null) {
	      return new ResponseEntity<>("Department ID is required", HttpStatus.BAD_REQUEST);
       }
	 
       List<Employee> allEmployeesWithPaging = employeeService.getAllEmployeesByDepartment(deptID);
       return new ResponseEntity<>(allEmployeesWithPaging, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getEmployeeById/{empID}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("empID") Long empId) {

       Employee employee = employeeService.getEmployeeById(empId);
       if(employee == null) {
    	   return new ResponseEntity<>("Employee not found for this id :: " + empId, HttpStatus.NOT_FOUND);
       }
       
       return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping(path = "/saveEmployee", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody EmployeeDTO employee) {
	  if(!employee.isEmployeeRequestValid()) {
		  Map<String, Object> resp = new HashMap<String, Object>();
		  resp.put("validRequest", employee.getValidRequest());
		  resp.put("validation-response", employee.getResponseMessage());
		  return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST); 
	  }
	  Employee respEmployee = null;
	  try {
		  if(employee.getDepartment() != null) {
		      Long deptid = employee.getDepartment().getDepartmentId();
		      
		      if(deptid == null) {
		    	  return new ResponseEntity<>("Department ID is Mandatory to save Employee", HttpStatus.BAD_REQUEST);
			  }
		      Department dept = deptService.getDepartmentById(deptid);
		      
		      if(dept != null) {
	            employee.setDepartment(dept);  
		      }	else {
		    	  return new ResponseEntity<>("Department with ID :: " + deptid + " does not exist", HttpStatus.BAD_REQUEST);
		      }
			}else {
		        return new ResponseEntity<>("Department is Mandatory to save Employee", HttpStatus.BAD_REQUEST);
		    }  
        respEmployee = employeeService.saveEmployee(employee.getEmployeeEntityBean());
	  }catch (Exception e) {
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
       return new ResponseEntity<>(respEmployee, HttpStatus.OK);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") Long empId,
	  @Valid @RequestBody EmployeeDTO employeeDetails)  {
		if(!employeeDetails.isEmployeeRequestValid()) {
			  Map<String, Object> resp = new HashMap<String, Object>();
			  resp.put("validRequest", employeeDetails.getValidRequest());
			  resp.put("validation-response", employeeDetails.getResponseMessage());
			  return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST); 
		  }
		try{
		Employee employee = employeeService.getEmployeeById(empId);
		
		if(employee == null) {
	       return new ResponseEntity<>("Employee not found for this id :: " + empId, HttpStatus.NOT_FOUND);
	    }
		
	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    employee.setEmail(employeeDetails.getEmail());
	    employee.setHireDate(employeeDetails.getHireDate());
	    employee.setPhoneNumber(employeeDetails.getPhoneNumber());
	    employee.setSalary(employeeDetails.getSalary());
	    employee.setManagerId(employeeDetails.getManagerId());
	    
	    if(employeeDetails.getDepartment() != null) {
	      Long deptid = employeeDetails.getDepartment().getDepartmentId();
	      Department dept = deptService.getDepartmentById(deptid);
	      
	      if(dept != null) {
            employee.setDepartment(dept);  
	      }	else {
	    	  return new ResponseEntity<>("Department with ID :: " + deptid + " does not exist", HttpStatus.BAD_REQUEST);
	      }
		}
	    final Employee updatedEmployee = employeeService.saveEmployee(employee);
	    return ResponseEntity.ok(updatedEmployee);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	    	   
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long empId)
	  {
		Employee employee = employeeService.getEmployeeById(empId);

		if(employee == null) {
	       return new ResponseEntity<>("Employee not found for this id :: " + empId, HttpStatus.NOT_FOUND);
	    }
		employeeService.deleteEmployee(employee);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("Employee Deleted Sucessfully :: ID : " + empId, Boolean.TRUE);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
