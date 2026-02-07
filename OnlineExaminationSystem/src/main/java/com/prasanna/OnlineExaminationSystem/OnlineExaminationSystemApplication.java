package com.prasanna.OnlineExaminationSystem;

import com.prasanna.OnlineExaminationSystem.Entity.User;
import com.prasanna.OnlineExaminationSystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineExaminationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineExaminationSystemApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Check if admin exists, if not, create one
			if (userRepository.findByUsername("superadmin").isEmpty()) {
				User admin = new User();
				admin.setUsername("superadmin");
				admin.setPassword(passwordEncoder.encode("admin@2026")); // Password is "admin123"
				admin.setRole("ADMIN"); // Matches your Security Config
				userRepository.save(admin);
				System.out.println("Admin user created: superadmin / admin@2026");
			}

			// Check if student exists, if not, create one
			if (userRepository.findByUsername("superstudent").isEmpty()) {
				User student = new User();
				student.setUsername("superstudent");
				student.setPassword(passwordEncoder.encode("student@2026"));
				student.setRole("STUDENT");
				userRepository.save(student);
				System.out.println("Student user created: superstudent / student@2026");
			}
		};
	}
}
