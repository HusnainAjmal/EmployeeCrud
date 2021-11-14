package com.systems.demo.employee.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.systems.demo.employee.crud.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmployeeId(Long empId);

	@Query(value = "SELECT * FROM employees e WHERE e.department_id = :deptID", 
			  nativeQuery = true)
	List<Employee> getEmployeesByDepartmentID( @Param("deptID") Long deptID);

	//Page<Employee> findEmployeeByDepartmentId(Long deptID, Pageable pageable);

}
