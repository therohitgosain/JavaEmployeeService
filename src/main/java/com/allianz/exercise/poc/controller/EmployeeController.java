package com.allianz.exercise.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.exercise.poc.model.Employee;
import com.allianz.exercise.poc.model.JwtRequest;
import com.allianz.exercise.poc.model.JwtTokenUtil;
import com.allianz.exercise.poc.service.EmployeeService;

/**
 * The Class EmployeeController.
 */
@RestController
public class EmployeeController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtProvider;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest
	 *            the authentication request
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@PostMapping("/authenticate")
	public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			return jwtProvider.createToken(authenticationRequest.getUsername());
		} catch (AuthenticationException e) {
			System.out.println("Log in failed for user, " + authenticationRequest.getUsername());
		}

		return "";
	}

	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	/**
	 * Adds the employee.
	 *
	 * @param newEmployee
	 *            the new employee
	 * @return the employee
	 */
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee newEmployee) {
		return employeeService.addEmployee(newEmployee);
	}

	// Single item

	/**
	 * Gets the employee.
	 *
	 * @param id
	 *            the id
	 * @return the employee
	 */
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return employeeService.getEmployee(id);
	}

	/**
	 * Replace employee.
	 *
	 * @param newEmployee
	 *            the new employee
	 * @param id
	 *            the id
	 * @return the employee
	 */
	@PutMapping("/employees/{id}")
	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeService.replaceEmployee(newEmployee, id);
	}

	/**
	 * Delete employee.
	 *
	 * @param id
	 *            the id
	 */
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}
}
