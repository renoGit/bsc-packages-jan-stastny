package com.prorocketeers.bscpackagesjanstastny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BscServiceApplication {
    public static void main(String[] args) {
        System.setProperty("debug", "true");
        SpringApplication.run(BscServiceApplication.class, args);
    }
}
