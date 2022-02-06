package com.localbakery;

import com.localbakery.oauth2.AppProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication (scanBasePackages = "com.localbakery")
@EnableConfigurationProperties(AppProperties.class)
public class LocalBakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalBakeryApplication.class, args);
    }

}
