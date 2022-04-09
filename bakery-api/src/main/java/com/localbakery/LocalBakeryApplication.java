package com.localbakery;

import com.localbakery.authentication.oauth2.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@SpringBootApplication (scanBasePackages = "com.localbakery")
@EnableConfigurationProperties(AppProperties.class)
@EnableWebMvc
public class LocalBakeryApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:aws.yml";



    public static void main(String[] args) {
//        SpringApplication.run(LocalBakeryApplication.class, args);


        new SpringApplicationBuilder(LocalBakeryApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;

    }

}
