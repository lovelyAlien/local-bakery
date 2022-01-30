package com.localbakery;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.localbakery.repository")
public class RepositoryConfig {
}
