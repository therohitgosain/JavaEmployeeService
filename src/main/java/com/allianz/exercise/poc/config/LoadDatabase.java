package com.allianz.exercise.poc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allianz.exercise.poc.model.Employee;
import com.allianz.exercise.poc.repository.EmployeeRepository;

/**
 * The Class LoadDatabase.
 */
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	/**
	 * Inits the database.
	 *
	 * @param repository the repository
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {

		//Initializing H2 database
		return args -> {
			log.info("Preloading " + repository.save(new Employee("RohitG1", "developer")));
			log.info("Preloading " + repository.save(new Employee("RohitG2", "tester")));
		};
	}
}