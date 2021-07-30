package com.example.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@PropertySource("classpath:properties.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftApplication.class, args);
    }

}
