package com.localbakery;

import org.springframework.context.annotation.Import;

@Import(value = RepositoryConfig.class)
public @interface EnableRepository {
}