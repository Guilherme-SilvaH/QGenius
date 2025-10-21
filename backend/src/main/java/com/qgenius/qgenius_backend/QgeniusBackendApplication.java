package com.qgenius.qgenius_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qgenius.qgenius_backend")
public class QgeniusBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(QgeniusBackendApplication.class, args);
    }
}
