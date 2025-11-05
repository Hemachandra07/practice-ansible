package com.klef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.entity.Employee;
import com.klef.repository.EmployeeRepository;

@Service
public  class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeerepository;

	@Override
	public Employee addEmployee(Employee employee) {
	
		return employeerepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeerepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		Optional<Employee> opt = employeerepository.findById(id);
        return opt.orElse(null);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return employeerepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(int id) {
		employeerepository.deleteById(id);	
		
	}
	

}
