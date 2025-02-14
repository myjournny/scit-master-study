package net.scit.spring7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	// ---> @LastModifiedDate 3)
public class Spring7Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring7Application.class, args);
	}

}
