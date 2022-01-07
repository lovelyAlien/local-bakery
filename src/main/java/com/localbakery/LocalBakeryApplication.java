package com.localbakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LocalBakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalBakeryApplication.class, args);
    }

}
