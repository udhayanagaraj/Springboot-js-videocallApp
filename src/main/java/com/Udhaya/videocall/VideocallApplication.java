package com.Udhaya.videocall;

import com.Udhaya.videocall.user.User;
import com.Udhaya.videocall.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VideocallApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideocallApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService service){
		return args -> {
			service.register(User.builder()
							.username("Ali")
							.email("ali@gmail.com")
							.password("aaa")
							.build());

			service.register(User.builder()
					.username("John")
					.email("john@gmail.com")
					.password("aaa")
					.build());

			service.register(User.builder()
					.username("Ana")
					.email("ana@gmail.com")
					.password("aaa")
					.build());
		};
	}

}
