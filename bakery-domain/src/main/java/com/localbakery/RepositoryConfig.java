package com.localbakery;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = {"com.localbakery.domain.repository"})
@EntityScan(value = "com.localbakery.domain.entity")
public class RepositoryConfig {
}
