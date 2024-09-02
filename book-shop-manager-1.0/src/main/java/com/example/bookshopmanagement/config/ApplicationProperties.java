package com.example.bookshopmanagement.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationProperties {

    @Value("${external-api.book-details-by-isbn-list}")
    String bookDetailsUrl;

}
