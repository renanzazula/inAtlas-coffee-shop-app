package com.inAtlas.coffeeShop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.inAtlas.coffeeShop"})
@PropertySource("classpath:application.properties")
public class TestConfiguration{ }
