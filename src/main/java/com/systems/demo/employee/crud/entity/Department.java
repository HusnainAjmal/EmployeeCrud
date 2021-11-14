package com.systems.demo.employee.crud.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

	@Id
	@Column(name = "department_id", length = 4)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_id_generator")
	@SequenceGenerator(name="dept_id_generator", sequenceName = "dept_id_seq", allocationSize=1)
	private Long departmentId;
	
	@Column(name = "department_name ", length = 30)
    @Size(max = 30 ,
    message = "Department Name can only be 30 Characters Long")
	private String departmentName;
		
	@Column(name = "manager_id", nullable = true, length = 6)
	private Long managerId;

	@OneToMany(mappedBy="department", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees;
	
	
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

	public Department(Long departmentId,
			@Size(max = 30, message = "Department Name can only be 30 Characters Long") String departmentName,
			Long managerId, List<Employee> employees) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.employees = employees;
	}

	public Department(Long departmentId,
			@Size(max = 30, message = "Department Name can only be 30 Characters Long") String departmentName,
			Long managerId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
	}

	public Department() {
		super();
	}
	
	
}
