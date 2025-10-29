package com.qgenius.qgenius_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.qgenius.qgenius_backend.infrastructure.client")
@SpringBootApplication(scanBasePackages = "com.qgenius.qgenius_backend")
public class QgeniusBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(QgeniusBackendApplication.class, args);
    }
}
