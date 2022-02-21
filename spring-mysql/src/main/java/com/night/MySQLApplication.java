package com.night;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.night"})
public class MySQLApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySQLApplication.class, args);
    }
}
