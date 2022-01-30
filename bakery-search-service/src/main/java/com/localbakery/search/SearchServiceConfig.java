package com.localbakery.search;

import com.localbakery.EnableRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableRepository
@Configuration
@ComponentScan(basePackages = {"com.localbakery.search.service"})
public class SearchServiceConfig {
}
