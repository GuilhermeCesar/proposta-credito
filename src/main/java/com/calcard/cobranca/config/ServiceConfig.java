package com.calcard.cobranca.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = DataConfiguration.class)
@ComponentScan
public class ServiceConfig {
}
