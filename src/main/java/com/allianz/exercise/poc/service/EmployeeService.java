package com.allianz.exercise.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.exercise.poc.exception.EmployeeNotFoundException;
import com.allianz.exercise.poc.model.Employee;
import com.allianz.exercise.poc.repository.EmployeeRepository;

/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	/**
	 * Adds the employee.
	 *
	 * @param newEmployee
	 *            the new employee
	 * @return the employee
	 */
	public Employee addEmployee(Employee newEmployee) {
		return repository.save(newEmployee);
	}

	/**
	 * Gets the employee.
	 *
	 * @param id
	 *            the id
	 * @return the employee
	 */
	public Employee getEmployee(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
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
	public Employee replaceEmployee(Employee newEmployee, Long id) {
		return repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	/**
	 * Delete employee.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}

}
