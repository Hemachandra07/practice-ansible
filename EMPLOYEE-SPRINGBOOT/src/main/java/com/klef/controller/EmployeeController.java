
package com.klef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.entity.Employee;
import com.klef.service.EmployeeService;

@RestController
@RequestMapping("/employeeapi/")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	  @PostMapping("/add")
	    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	        Employee savedEmployee = employeeservice.addEmployee(employee);
	        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	    }
	  
	  
	  @GetMapping("/all")
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> employees = employeeservice.getAllEmployees();
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }
	  
	  
	  @GetMapping("/get/{id}")
	  public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
	      Employee employee = employeeservice.getEmployeeById(id);
	      if (employee != null) {
	          return new ResponseEntity<>(employee, HttpStatus.OK);
	      } else {
	          return new ResponseEntity<>("Employee with ID " + id + " not found.", HttpStatus.NOT_FOUND);
	      }
	  }

	  @PutMapping("/update")
	  public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
	      Employee existing = employeeservice.getEmployeeById(employee.getId());
	      if (existing != null) {
	          Employee updatedEmployee = employeeservice.updateEmployee(employee);
	          return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	      } else {
	          return new ResponseEntity<>("Cannot update. Employee with ID " + employee.getId() + " not found.", HttpStatus.NOT_FOUND);
	      }
	  }

	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
	      Employee existing = employeeservice.getEmployeeById(id);
	      if (existing != null) {
	          employeeservice.deleteEmployeeById(id);
	          return new ResponseEntity<>("Employee with ID " + id + " deleted successfully.", HttpStatus.OK);
	      } else {
	          return new ResponseEntity<>("Cannot delete. Employee with ID " + id + " not found.", HttpStatus.NOT_FOUND);
	      }
	  }


	

}
