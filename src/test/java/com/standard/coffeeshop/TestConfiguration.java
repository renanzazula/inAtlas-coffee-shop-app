package com.standard.coffeeshop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.standard.coffeeshop"})
@PropertySource("classpath:application.properties")
public class TestConfiguration{ }
