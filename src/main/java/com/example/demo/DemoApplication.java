package com.example.demo;

import com.example.demo.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    private StudentRepository studentRepository;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("is Running..");
    }

}
