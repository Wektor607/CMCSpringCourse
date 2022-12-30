package ru.mikhelson.springcourse.SpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpecialCourseSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpecialCourseSpringApplication.class, args);
        System.out.println("Server start working!");
    }

}
