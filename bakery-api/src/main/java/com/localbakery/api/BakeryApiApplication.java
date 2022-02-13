package com.localbakery.api;

import com.localbakery.EnableBakeryRepository;
import com.localbakery.EnableSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableBakeryRepository
@EnableSearchService
@EnableWebMvc
//@EnableDataProvider
public class BakeryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryApiApplication.class, args);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;

    }



}
