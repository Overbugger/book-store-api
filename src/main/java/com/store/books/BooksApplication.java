package com.store.books;

import com.store.books.role.Role;
import com.store.books.role.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepo roleRepo) {
		return args -> {
			if (roleRepo.findByName("USER").isEmpty()) {
				roleRepo.save(Role.builder().name("USER").build());
			}
		};
	}
}
