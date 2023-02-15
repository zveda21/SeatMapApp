package com.sourcemind.seatmapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SeatMapServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatMapServerApplication.class, args);
    }

}
