package com.klef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee findByEmail(String email);

}
