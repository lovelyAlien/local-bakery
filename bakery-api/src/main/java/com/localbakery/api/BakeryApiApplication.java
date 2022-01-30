package com.localbakery.api;

import com.localbakery.EnableBakeryRepository;
import com.localbakery.EnableSearchService;
import com.localbakery.api.config.ApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableBakeryRepository
@EnableSearchService
@Import(value = {ApiConfiguration.class})
public class BakeryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryApiApplication.class, args);
    }

}
