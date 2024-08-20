package com.emazon.stock.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@PropertySource(value = "classpath:category.properties", encoding = "UTF-8")
@Configuration
public class ConfigProperties {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:category");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
