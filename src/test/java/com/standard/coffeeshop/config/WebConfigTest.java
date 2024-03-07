package com.standard.coffeeshop.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@TestPropertySource(properties = {"server.port=8088", "server.servlet.context-path=/standard-coffeeShopApp"})
class WebConfigTest {

    MockMvc mockMvc;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String path;

    @BeforeEach
    void setUp() {

// FIXME: at the moment we have disable this test
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://" + path + ":" + port)
//                        .allowedMethods(RequestMethod.GET.name(),
//                                        RequestMethod.POST.name(),
//                                        RequestMethod.PUT.name(),
//                                        RequestMethod.DELETE.name())
//                        .allowedHeaders("*");;
//            }
//        };
//
//        // Creating MockMvc instance and applying the WebMvcConfigurer
//        mockMvc = MockMvcBuilders.standaloneSetup()
//                .setControllerAdvice(webMvcConfigurer)
//                .build();
    }

    @Test
    @Disabled
    void addCorsMappings() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:8080"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"));
    }

    @Test
    void testPortValue() {
        assertEquals("8088", port);
    }

    @Test
    void testPathValue() {
        assertEquals("/standard-coffeeShopApp", path);
    }
}