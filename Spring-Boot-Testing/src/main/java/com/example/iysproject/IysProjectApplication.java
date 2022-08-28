package com.example.iysproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IysProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(IysProjectApplication.class, args);
	}

	@GetMapping("/hello")
	public static String sayHello(@RequestParam(value = "theName", defaultValue = "World") String name) {
		return "Hello " + name;
	}
}