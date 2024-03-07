package com.standard.coffeeshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${server.port}")
    private String port;


    @Value("${server.servlet.context-path}")
    private String path;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://" + path + ":" + port)
                .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name(), RequestMethod.DELETE.name())
                .allowedHeaders("*"); // Specify the request headers allowed

        /**
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE","HEAD", "OPTIONS")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "X-Auth-Token", "X-Csrf-Token", "Authorization")
                .exposedHeaders("token_header1", "token_header2")
                .allowCredentials(false)
                .maxAge(3_600);
        **/
    }

}
