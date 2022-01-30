package com.localbakery.api;

import com.localbakery.EnableBakeryRepository;
import com.localbakery.EnableDataProvider;
import com.localbakery.EnableSearchService;
import com.localbakery.api.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableBakeryRepository
@EnableSearchService
@EnableDataProvider
@Import(value = {SwaggerConfig.class})
public class BakeryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryApiApplication.class, args);
    }

}
