package com.desacode.disruptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DisruptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisruptorApplication.class, args);
	}

}
