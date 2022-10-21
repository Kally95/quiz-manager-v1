package com.example.quizmanagerv1;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class QuizManagerV1Application {
	public static void main(String[] args) {
		SpringApplication.run(QuizManagerV1Application.class, args);
	}
}
