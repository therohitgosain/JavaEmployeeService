package com.allianz.exercise.poc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import com.allianz.exercise.poc.model.Employee;
import com.allianz.exercise.poc.repository.EmployeeRepository;
import com.allianz.exercise.poc.service.EmployeeService;

/**
 * The Class EmployeeServiceTest.
 */
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository repository;

	/**
	 * Gets the employee test.
	 *
	 * @return the employee test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getEmployeeTest() throws Exception {
		ArrayList<Employee> employeeList = new ArrayList<>((Arrays.asList(new Employee("RohitG1", "developer"))));
		when(repository.findAll()).thenReturn(employeeList);
		List<Employee> allEmployees = employeeService.getAllEmployees();
		assertThat(allEmployees).isNotNull().isNotEmpty().allMatch(p -> p.getName().toLowerCase().contains("rohitg1"));
	}

	/**
	 * Gets the employe test.
	 *
	 * @return the employe test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getEmployeTest() throws Exception {
		when(repository.findById(any())).thenReturn(Optional.of(new Employee("RohitG1", "developer")));
		Employee employee = employeeService.getEmployee(1L);
		assertThat(employee).isNotNull();
	}

}
