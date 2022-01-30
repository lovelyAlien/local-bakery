package com.localbakery;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBakeryRepository
@ComponentScan(value = {"com.localbakery.query", "com.localbakery.search"})
public class SearchServiceConfig {
}
