package com.allianz.exercise.poc.exception;

/**
 * The Class EmployeeNotFoundException.
 */
public class EmployeeNotFoundException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
	    super("Could not find employee " + id);
	  }
	}