package com.allianz.exercise.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allianz.exercise.poc.model.Employee;

/**
 * The Interface EmployeeRepository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}