package com.allianz.exercise.poc.integrationTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.allianz.exercise.poc.model.Employee;
import com.allianz.exercise.poc.model.JwtTokenUtil;
import com.allianz.exercise.poc.service.EmployeeService;

/**
 * The Class EmployeeRestControllerIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtProvider;

	@MockBean
	private EmployeeService employeeService;

	/**
	 * Gets the employee test.
	 *
	 * @return the employee test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getEmployeeTest() throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("allianz", "allianz"));
		String token = jwtProvider.createToken("allianz");
		System.out.println("token-->" + token);

		ArrayList<Employee> employeeList = new ArrayList<>(
				(Arrays.asList(new Employee("RohitG1", "developer"), new Employee("RohitG2", "tester"))));
		when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>(
				Arrays.asList(new Employee("RohitG1", "developer"), new Employee("RohitG2", "tester"))));
		final ResultActions result = this.mvc.perform(
				get("/employees").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].name").value(employeeList.get(0).getName()));
	}
}