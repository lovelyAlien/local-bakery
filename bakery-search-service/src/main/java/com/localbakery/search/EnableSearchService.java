package com.localbakery.search;

import org.springframework.context.annotation.Import;

@Import(value = SearchServiceConfig.class)
public @interface EnableSearchService {
}
