package com.vini.dev.twapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TwapiApplication {
	public static void main(final String[] args) {
		SpringApplication.run(TwapiApplication.class, args);
	}
}

@Configuration
class AppConfig {

	@Bean
	Bird Test () {
		System.out.println("************ Sou um Bean *************");
		return new Bird();
	}
}

class Bird  {}
