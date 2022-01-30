package com.localbakery;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.localbakery.domain.dataprovider"})
public class DataProviderConfig {
}
