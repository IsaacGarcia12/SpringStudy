package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student joy  = new Student("joy", LocalDate.of(1996, Month.SEPTEMBER, 3), "joyRV@gmail.com");
			Student irene  = new Student("irene", LocalDate.of(1991, Month.MARCH, 29), "ireneRV@gmail.com");
			repository.saveAll(List.of(joy, irene));
		};
	}
	
}
