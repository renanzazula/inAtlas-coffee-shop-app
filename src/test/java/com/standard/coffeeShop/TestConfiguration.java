package com.standard.coffeeShop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.standard.coffeeShop"})
@PropertySource("classpath:application.properties")
public class TestConfiguration{ }
