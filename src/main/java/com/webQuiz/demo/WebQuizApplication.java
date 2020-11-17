package com.webQuiz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@ComponentScan("com.webQuiz.demo.repositories")*/
public class WebQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebQuizApplication.class, args);
	}

}
